package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import es.ucm.fdi.iw.model.Review;

public class ReviewController {
    @Autowired
	private EntityManager entityManager;

    public void insertMentoring(Review mentoria){

        entityManager.persist(mentoria);
        entityManager.flush();
    }
}
