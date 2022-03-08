package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import es.ucm.fdi.iw.model.Tag;

public class TagController {
    @Autowired
	private EntityManager entityManager;

    public void insertDB(Tag mentoria){

        entityManager.persist(mentoria);

    }

}
