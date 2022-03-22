package es.ucm.fdi.iw.controller;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

    @RequestMapping(value = "/buscarTagPorNombre", method = RequestMethod.POST)
    public String buscarTag(Model model, String nombre){
        List<Tag> tags = busquedaPorNombre(nombre);
        model.addAttribute("tags", tags);
        return "lista_tags";
    }



    private List<Tag> busquedaPorNombre(String nombre){
        TypedQuery<Tag> queryAux =  entityManager.createNamedQuery("TAG.byNAME", Tag.class).setParameter("NAME", nombre);

        List<Tag> tags = queryAux.getResultList();
        return tags;
    }
    




}
