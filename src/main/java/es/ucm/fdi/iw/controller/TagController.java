package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
    @Transactional
    @RequestMapping(value = "/crearTag", method = RequestMethod.POST)
    public String createTag(Model model, @ModelAttribute Tag tag){
        entityManager.persist(tag);
        return "crear_tag";
    }
    @Transactional
    @RequestMapping(value = "/actualizarTag", method = RequestMethod.POST)
    public String updateTag(Model model, @ModelAttribute Tag tag){
        Tag tagAux = entityManager.find(Tag.class, tag.getId());

        tagAux.setName(tag.getName());
        tagAux.setDescription(tag.getDescription());
        return "actualizar_tag";
    }
    @Transactional
    @RequestMapping(value = "/borrarTag", method = RequestMethod.POST)
    public String borrarTag(Model model, @ModelAttribute Tag tag){
        Tag tagAux = entityManager.getReference(Tag.class, tag.getId());
        entityManager.remove(tagAux);
        return "borrar_tag";
    }





}
