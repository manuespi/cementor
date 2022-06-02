package es.ucm.fdi.iw.controller;

import java.util.Collection;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ucm.fdi.iw.model.Asesor;


/**
 *  Site administration.
 *
 *  Access to this end-point is authenticated.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	private static final Logger log = LogManager.getLogger(AdminController.class);

	@GetMapping("/")
    public String index(Model model) {
        return "admin";
    }
    //Tag
    @Autowired
	private EntityManager entityManager;

    
}
