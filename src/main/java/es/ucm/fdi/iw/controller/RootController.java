package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.ucm.fdi.iw.model.Comment;
import es.ucm.fdi.iw.model.Mentoring;
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
    @GetMapping("/crear_tag")
    public String crearTag(Model model) {
        //model.addAttribute("tag", new Tag());
        return "crear_tag";
    }
    
    @Transactional
    @GetMapping("/crear_comment")
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

        return "crear_comment";
    }
    
    @Transactional
    @PostMapping("/crearComment")
    public String createComment(Model model,HttpSession session, @ModelAttribute Comment comment){
        Long id = ((User) session.getAttribute("u")).getId();
        User u = entityManager.find(User.class, id);
        comment.setUser(u);
        entityManager.persist(comment);
        return "crear_comment";
    }

    @GetMapping("/crear_mentoria")
    public String vistaCrearMentoria(Model model){
        return "crear_mentoria";
    }
    @Transactional
    @PostMapping("/crearMentoria")
    public String crearMentoring(Model model, HttpSession session, @ModelAttribute Mentoring mentoria) {
        //model.addAttribute("tag", new Tag());
            Long id = ((User) session.getAttribute("u")).getId();
            User u = entityManager.find(User.class, id);
        
            mentoria.setMentor(u);
            entityManager.persist(mentoria);
            

        return "crear_mentoria";
    }
	@GetMapping("/")
    public String index(Model model) {
        return "index";
    }
/*
    @GetMapping("/{cosa}")
    public String unaCosa(@PathVariable String cosa, Model model) {
        return cosa;
    }
*/    
}
