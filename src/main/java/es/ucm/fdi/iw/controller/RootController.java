package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.ucm.fdi.iw.model.Comment;
import es.ucm.fdi.iw.model.Mentoring;
import es.ucm.fdi.iw.model.Review;
import es.ucm.fdi.iw.model.Tag;
import es.ucm.fdi.iw.model.User;

import org.springframework.web.bind.annotation.*;


/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

	private static final Logger log = LogManager.getLogger(RootController.class);

    @Autowired
	private EntityManager entityManager;

	@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @GetMapping("/tags/crear_tag")
    public String vistacrearTag(Model model) {
        //model.addAttribute("tag", new Tag());
        return "tags/crear_tag";
    }
    @Transactional
    @PostMapping("/tags/crear_tag")
    public String crearTag(Model model, HttpSession session, @ModelAttribute Tag tag) {
        //model.addAttribute("tag", new Tag());
            entityManager.persist(tag);
            return "tags/crear_tag";
    }
    
    @GetMapping("/tags/lista_tags")
    public String vistaListaTags(Model model) {
        model.addAttribute("tagList", entityManager
            .createQuery("SELECT t FROM Tag t", Tag.class)
            .getResultList());
        return "/tags/lista_tags";
    }
    @Transactional
    @GetMapping("/comments/crear_comment")
    public String crearComment(Model model) {
        //model.addAttribute("tag", new Tag());
        Tag tag = new Tag();
        if(tag.setName("IW")!=true){
            System.out.println("Error");
        }
        tag.setDescription("iajsdioajsiodj");
        entityManager.persist(tag);
        entityManager.flush();

        tag = new Tag();
        if(tag.setName("SAW")!=true){
            System.out.println("Error");
        }
        tag.setDescription("iajsdioajsiodj");
        entityManager.persist(tag);
        entityManager.flush();

        tag = new Tag();
        if(tag.setName("FWES")!=true){
            System.out.println("Error");
        }
        tag.setDescription("iajsdioajsiodj");
        entityManager.persist(tag);
        entityManager.flush();



        model.addAttribute("tagList", entityManager
            .createQuery("SELECT t FROM Tag t", Tag.class)
            .getResultList());

        return "comments/crear_comment";
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
        //model.addAttribute("tag", new Tag());
            Long id = ((User) session.getAttribute("u")).getId();
            User u = entityManager.find(User.class, id);
            mentoria.setMentor(u);
            List<Tag> listTags= new ArrayList<Tag>();
            for(int i=0; i<ids.size(); i++){
                listTags.add(entityManager.find(Tag.class, ids.get(i)));
            }
            mentoria.setTags(listTags);
            entityManager.persist(mentoria);
            return "mentorias/crear_mentoria";
    }
	@GetMapping("/")
    public String index(Model model) {
        return "index";
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
    public String createReview(Model model,HttpSession session, @ModelAttribute Review review){
        Long id = ((User) session.getAttribute("u")).getId();
        User u = entityManager.find(User.class, id);
        review.setCreator(u);
        entityManager.persist(review);
        return "reviews/crear_review";
    }
/*
    @GetMapping("/{cosa}")
    public String unaCosa(@PathVariable String cosa, Model model) {
        return cosa;
    }
*/    
}
