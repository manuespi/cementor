package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ucm.fdi.iw.model.Tag;


@Controller
@RequestMapping("/tag")
public class TagController {
    @Autowired
	private EntityManager entityManager;

    @RequestMapping(value = "/crearTag", method = RequestMethod.POST)
    public String createTag(Model model, @ModelAttribute Tag tag){
       return "redirect:/login";
    }
    public void insertDB(Tag tag){

        entityManager.persist(tag);
        entityManager.flush();
    }

}
