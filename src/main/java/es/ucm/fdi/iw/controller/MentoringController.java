package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import es.ucm.fdi.iw.model.Mentoring;

public class MentoringController {
    
    @Autowired
	private EntityManager entityManager;

    public void insertMentoring(Mentoring mentoria){

        entityManager.persist(mentoria);
        entityManager.flush();
    }
}
