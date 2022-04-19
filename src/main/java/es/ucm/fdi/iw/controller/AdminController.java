package es.ucm.fdi.iw.controller;

import java.util.Collection;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ucm.fdi.iw.model.Comment;
import es.ucm.fdi.iw.model.Tag;

/**
 *  Site administration.
 *
 *  Access to this end-point is authenticated.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	private static final Logger log = LogManager.getLogger(AdminController.class);

	@GetMapping("/")
    public String index(Model model) {
        return "admin";
    }
    //Tag
    @Autowired
	private EntityManager entityManager;
   

    @Transactional
    @RequestMapping(value = "/actualizarTag", method = RequestMethod.POST)
    public String updateTag(Model model, @ModelAttribute Tag tag){
        Tag tagAux = entityManager.find(Tag.class, tag.getId());

        if(tagAux.setName(tag.getName())){
            if(tagAux.setDescription(tag.getDescription()))
            return "actualizar_tag";
        }
        return "actualizar_tag"; //poner un alert?
    }

    @Transactional
    @RequestMapping(value = "/borrarTag", method = RequestMethod.POST)
    public String borrarTag(Model model, @ModelAttribute Tag tag){
        Tag tagAux = entityManager.getReference(Tag.class, tag.getId());
        entityManager.remove(tagAux);
        return "borrar_tag";
    }


    //Comment
    @RequestMapping(value = "/VerTag", method = RequestMethod.POST)
    public String borrarTag(Model model, @ModelAttribute Comment comment){
        model.addAttribute("tagList", entityManager
            .createQuery("SELECT t FROM TAG t",Tag.class)
            .getResultList());
        return "borrar_comment";
    }
}
