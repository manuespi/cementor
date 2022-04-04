package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ucm.fdi.iw.model.Mentoring;

@Controller
@RequestMapping("mentoring")
public class MentoringController {
    
    @Autowired
	private EntityManager entityManager;

    public void insertMentoring(Mentoring mentoria){

        entityManager.persist(mentoria);
        entityManager.flush();
    }

    @GetMapping("/pag_crear_mentoria")
    public String index(Model model) {
        return "crear_mentoria";
    }
}

