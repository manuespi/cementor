package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.ucm.fdi.iw.model.Tag;

import org.springframework.web.bind.annotation.*;


/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

	private static final Logger log = LogManager.getLogger(RootController.class);

	@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping("/crear_tag")
    public String crearTag(Model model) {
        //model.addAttribute("tag", new Tag());
        System.out.println("hola");
        return "crear_tag";
    }
    public String crearComment(Model model) {
        //model.addAttribute("tag", new Tag());
        System.out.println("hola");
        return "crear_comment";
    }
	@GetMapping("/")
    public String index(Model model) {
        return "index";
    }
/*
    @GetMapping("/{cosa}")
    public String unaCosa(@PathVariable String cosa, Model model) {
        return cosa;
    }
*/    
}
