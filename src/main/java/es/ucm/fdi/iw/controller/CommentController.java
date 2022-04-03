package es.ucm.fdi.iw.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;


import es.ucm.fdi.iw.model.Comment;
import es.ucm.fdi.iw.model.Tag;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
	private EntityManager entityManager;

    @RequestMapping(value = "/VerTag", method = RequestMethod.POST)
    public String borrarTag(Model model, @ModelAttribute Comment comment){
        model.addAttribute("tagList", entityManager
            .createQuery("SELECT t FROM TAG t",Tag.class)
            .getResultList());
        return "borrar_comment";
    }
    

   /* @Transactional
    @RequestMapping(value = "/actualizarComment", method = RequestMethod.POST)
    public String updateTag(Model model, @ModelAttribute Comment comment){
        Comment commentAux = entityManager.find(Comment.class, comment.getId());

        if(commentAux.setName(comment.getName())){
            if(tagAux.setDescription(tag.getDescription()))
            return "actualizar_tag";
        }
        return " "; //poner un alert?
    }*/
    @Transactional
    @RequestMapping(value = "/borrarComment", method = RequestMethod.POST)
    public String borrarComment(Model model, @ModelAttribute Comment comment){
        Comment commentAux = entityManager.getReference(Comment.class, comment.getId());
        entityManager.remove(commentAux);
        return "borrar_comment";
    }
}