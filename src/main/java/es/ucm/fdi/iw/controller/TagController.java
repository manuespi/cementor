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

    @RequestMapping(value = "/buscarTagPorNombre", method = RequestMethod.POST)
    public String buscarTag(Model model, String nombre){ //esta es para user y para admin
        List<Tag> tags = busquedaPorNombre(nombre);
        model.addAttribute("tags", tags);
        return "lista_tags";
    }



    private List<Tag> busquedaPorNombre(String nombre){ //esta es para user y para admin
        TypedQuery<Tag> queryAux =  entityManager.createNamedQuery("TAG.byNAME", Tag.class).setParameter("NAME", nombre);

        List<Tag> tags = queryAux.getResultList();
        return tags;
    }
    




}
