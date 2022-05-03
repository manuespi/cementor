package es.ucm.fdi.iw.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.ZoneId;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.ucm.fdi.iw.model.Comment;
import es.ucm.fdi.iw.model.Mentoring;
import es.ucm.fdi.iw.model.Review;
import es.ucm.fdi.iw.model.Tag;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.User.Role;

import org.springframework.web.bind.annotation.*;


/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

	private static final Logger log = LogManager.getLogger(RootController.class);

    @Autowired
	private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
    public String vistaLogin(Model model) {
        return "login";
    }


    @GetMapping("/crear_cuenta")
    public String vistaCrearCuenta(Model model){
        return "crear_cuenta";
    }

    @Transactional
    @PostMapping("/crear_cuenta")
    public String crearCuenta(Model model, @ModelAttribute User user){
        boolean found = true;
        try {
            User u = entityManager.createNamedQuery("User.byUsername", User.class)
            .setParameter("username", user.getUsername())
            .getSingleResult();
        } catch (NoResultException nre) {
            found = false;
        }
        if (found) {
            model.addAttribute("error", "Nombre de usuario ya cogido. Sé más original.");
            return "/crear_cuenta";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setScore(0.0);
        
        entityManager.persist(user);

        
        return "crear_cuenta";
    }

    @GetMapping("/tags/crear_tag")
    public String vistaCrearTag(Model model) {
        return "tags/crear_tag";
    }
    @Transactional
    @PostMapping("/tags/crear_tag")
    public String crearTag(Model model, HttpSession session, @ModelAttribute Tag tag) {
            entityManager.persist(tag);
            return "tags/crear_tag";
    }

    @GetMapping("/tags/actualizar_tag")
    public String vistaActualizarTag(Model model) {
        return "tags/lista_tags";
    }
    @Transactional
    @ResponseBody
    @PostMapping("/tags/actualizar_tag")
    public String actualizarTag(Model model, HttpSession session, @RequestBody JsonNode data) {
        Tag t = entityManager.find(Tag.class, data.get("id").asLong());
        t.setDescription(data.get("description").asText());
        t.setName(data.get("name").asText());
        return "{\"result\": \"ok\"}";
    }

    @GetMapping("/tags/borrar_tag")
    public String vistaBorrarTag(Model model) {
        return "tags/lista_tags";
    }
    @Transactional
    @ResponseBody
    @PostMapping("/tags/borrar_tag")
    public String borrarTag(Model model, HttpSession session, @RequestBody JsonNode data) {
        Tag t = entityManager.find(Tag.class, data.get("id").asLong());
        entityManager.remove(t);
        return "{\"result\": \"ok\"}";
        
    }

    @GetMapping("/tags/lista_tags")
    public String vistaListaTags(Model model) {
        model.addAttribute("tagList", entityManager
            .createQuery("SELECT t FROM Tag t", Tag.class)
            .getResultList());
        return "/tags/lista_tags";
    }
    @GetMapping("/comments/lista_comments")
    public String vistaListaComments(Model model) {
        model.addAttribute("commentList", entityManager
            .createQuery("SELECT c FROM Comment c", Comment.class)
            .getResultList());
        return "/comments/lista_comments";
    }

    @Transactional
    @GetMapping("/comments/crear_comment")
    public String crearComment(Model model) {
        Tag tag = new Tag();
        tag.setName("IW");
        tag.setDescription("iajsdioajsiodj");
        entityManager.persist(tag);
        entityManager.flush();

        tag = new Tag();
        tag.setName("SAW");
        tag.setDescription("iajsdioajsiodj");
        entityManager.persist(tag);
        entityManager.flush();

        tag = new Tag();
        tag.setName("FWES");
        tag.setDescription("iajsdioajsiodj");
        entityManager.persist(tag);
        entityManager.flush();



        model.addAttribute("tagList", entityManager
            .createQuery("SELECT t FROM Tag t", Tag.class)
            .getResultList());

        return "comments/crear_comment";
    }

    @GetMapping("/comments/borrar_comment")
    public String vistaBorrarComment(Model model) {
        return "comments/lista_comment";
    }
    @Transactional
    @ResponseBody
    @PostMapping("/comments/borrar_comment")
    public String borrarComment(Model model, HttpSession session, @RequestBody JsonNode data) {
        Comment t = entityManager.find(Comment.class, data.get("id").asLong());
        entityManager.remove(t);
        return "{\"result\": \"ok\"}"; 
    }

    @Transactional
    @PostMapping("/crearComment")
    public String createComment(Model model,HttpSession session, @ModelAttribute Comment comment, @RequestParam(name = "tags", required = false) List<Long> ids){
        Long id = ((User) session.getAttribute("u")).getId();
        User u = entityManager.find(User.class, id);
        comment.setUser(u);
        List<Tag> listTags= new ArrayList<Tag>();
        for(int i=0; i<ids.size(); i++){
            listTags.add(entityManager.find(Tag.class, ids.get(i)));
        }
        comment.setTag(listTags);
        LocalDate aux = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        comment.setDate(Date.from(aux.atStartOfDay(defaultZoneId).toInstant()));
        entityManager.persist(comment);
        return "comments/crear_comment";
    }

    //Ver comments
    @Transactional
    @GetMapping("/comments/ver_comments")
    public String verComment(Model model)
    {
        model.addAttribute("commentList", entityManager
            .createQuery("SELECT t FROM Comment t", Comment.class)
            .getResultList());

        return "comments/ver_comments";
    }

    @GetMapping("/comments/ver_comments/{id}")
    public String verCommentId(Model model, @PathVariable("id") String id)
    {
        model.addAttribute("commentList", entityManager
            .createQuery("SELECT m FROM Comment m INNER JOIN m.tag r WHERE r.id=1", Comment.class)
            .getResultList());
            
        return "comments/ver_comments";
    }
    //@PostMapping("comments/ver_comments")

    @GetMapping("/mentorias/crear_mentoria")
    public String vistaCrearMentoria(Model model){
        model.addAttribute("tagList", entityManager
            .createQuery("SELECT t FROM Tag t", Tag.class)
            .getResultList());

        return "/mentorias/crear_mentoria";
    }
    @Transactional
    @PostMapping("/mentorias/crear_mentoria")
    public String crearMentoring(Model model, HttpSession session, @ModelAttribute Mentoring mentoria, @RequestParam(name = "tagIds", required = false) List<Long> ids) {
            Long id = ((User) session.getAttribute("u")).getId();
            User u = entityManager.find(User.class, id);
            mentoria.setMentor(u);
            List<Tag> listTags= new ArrayList<Tag>();
            for(int i=0; i<ids.size(); i++){
                listTags.add(entityManager.find(Tag.class, ids.get(i)));
            }
            mentoria.setTag(listTags);
            entityManager.persist(mentoria);
            return "mentorias/crear_mentoria";
    }
	@GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    
    @GetMapping("/mentorias/lista_mentorias")
    public String verListaMentorings(Model model) {
            model.addAttribute("mentoringList", entityManager
            .createQuery("SELECT m FROM Mentoring m", Mentoring.class)
            .getResultList());

        return "mentorias/lista_mentorias";
    }



    @Transactional
    @GetMapping("/reviews/crear_review")
    public String crearReview(Model model) {
            entityManager.flush();
            model.addAttribute("mentoringList", entityManager
            .createQuery("SELECT m FROM Mentoring m", Mentoring.class)
            .getResultList());

        return "reviews/crear_review";
    }
    @Transactional
    @PostMapping("/reviews/crearReview")
    public String createReview(Model model,HttpSession session, @ModelAttribute Review review,  @RequestParam(name = "mentoringId", required = false) Long ids){
        Long id = ((User) session.getAttribute("u")).getId();
        User u = entityManager.find(User.class, id);
        review.setCreator(u);
        Mentoring m = entityManager.find(Mentoring.class, ids);
        review.setMentoring(m);
        entityManager.persist(review);
        return "/reviews/crear_review";
    }

    @GetMapping("/reviews/lista_reviews")
    public String verListaReviews(Model model) {
            model.addAttribute("reviewList", entityManager
            .createQuery("SELECT r FROM Review r", Review.class)
            .getResultList());

        return "reviews/lista_reviews";
    }
  

    /*@GetMapping("/reviews/crear_review")
    public String mentoringListForReview(Model model) {
            model.addAttribute("mentoringList", entityManager
            .createQuery("SELECT m FROM Mentoring m", Mentoring.class)
            .getResultList());

        return "/reviews/crear_review";
    }*/

   /* @Transactional
    @GetMapping("/reviews/crear_review")
    public String crearReview(Model model) {
            entityManager.flush();
            model.addAttribute("mentoringList", entityManager
            .createQuery("SELECT m FROM Mentoring m", Mentoring.class)
            .getResultList());

        return "reviews/crear_review";
    }*/

 
/*
    @GetMapping("/{cosa}")
    public String unaCosa(@PathVariable String cosa, Model model) {
        return cosa;
    }
*/
}
