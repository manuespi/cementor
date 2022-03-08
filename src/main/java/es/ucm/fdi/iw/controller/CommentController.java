package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import es.ucm.fdi.iw.model.Comment;

public class CommentController {
   
    @Autowired
	private EntityManager entityManager;

    public void insertMentoring(Comment mentoria){

        entityManager.persist(mentoria);

    }
}
