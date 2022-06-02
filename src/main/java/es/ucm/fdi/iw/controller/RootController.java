package es.ucm.fdi.iw.controller;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.ZoneId;
import java.time.ZoneOffset;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.websocket.Session;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.ucm.fdi.iw.model.Asesor;
import es.ucm.fdi.iw.model.ChatMessage;
import es.ucm.fdi.iw.model.Medicion;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.Medicion.Tipo;
import es.ucm.fdi.iw.model.User.Role;

import org.springframework.web.bind.annotation.*;


/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

	private static final Logger log = LogManager.getLogger(RootController.class);

    @Autowired
	private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String pantallaPrincipal(Model model, HttpSession session) {
        if(((User) session.getAttribute("u"))!=null){
            model.addAttribute("medicionesList", entityManager
            .createQuery("SELECT m FROM Medicion m", Medicion.class)
            .getResultList());

            model.addAttribute("asesorList", entityManager
            .createQuery("SELECT r FROM Asesor r WHERE activo = true", Asesor.class)
            .getResultList());

            List<Tipo> tipos=new ArrayList<Tipo>();
            tipos.add(Tipo.MENOR);
            tipos.add(Tipo.INTERMEDIA);
            tipos.add(Tipo.SUPERIOR);
            model.addAttribute("tiposMediciones", tipos);
            return "inicio";
        }
        return "login";
    }

	@GetMapping("/login")
    public String vistaLogin(Model model) {
        return "login";
    }


    @GetMapping("/crear_cuenta")
    public String vistaCrearCuenta(Model model){
        return "crear_cuenta";
    }

    @Transactional
    @PostMapping("/crear_cuenta")
    public String crearCuenta(Model model, @ModelAttribute User user, HttpSession session){
        boolean found = true;
        try {
            User u = entityManager.createNamedQuery("User.byUsername", User.class)
            .setParameter("username", user.getUsername())
            .getSingleResult();
        } catch (NoResultException nre) {
            found = false;
        }
        if (found) {
            model.addAttribute("error", "Nombre de usuario ya cogido. Sé más original.");
            return "/crear_cuenta";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles("USER");
        
        entityManager.persist(user);
        entityManager.flush();
        
        
        return "crear_cuenta";
    }
    
    @GetMapping("/mediciones/annadir_medicion")
    public String verAnnadirMedicion(Model model, HttpSession session) {
            model.addAttribute("asesorList", entityManager
            .createQuery("SELECT r FROM Asesor r WHERE activo = true", Asesor.class)
            .getResultList());

            List<Tipo> tipos=new ArrayList<Tipo>();
            tipos.add(Tipo.MENOR);
            tipos.add(Tipo.INTERMEDIA);
            tipos.add(Tipo.SUPERIOR);
            model.addAttribute("tiposMediciones", tipos);


        return "mediciones/annadir_medicion";
    }

    @Transactional
    @PostMapping("/mediciones/annadir_medicion")
    public String annadirMedicion(Model model, HttpSession session, @ModelAttribute Medicion medicion, @RequestParam(name = "asesor", required = true) Long idAsesor, @RequestParam(name = "tipoSelected", required = true) String tipo ) {
        medicion.setAtendida(false);
        medicion.setLlamada(false);
        medicion.setRecibida(false);
        LocalDate aux = LocalDate.now();
        LocalTime aux1= LocalTime.now();
        LocalDateTime localDateTime=aux1.atDate(aux);

        ZoneId defaultZoneId = ZoneId.systemDefault();
        //medicion.setFecha(Date.from(aux.atStartOfDay(defaultZoneId).toInstant()));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        medicion.setFecha(Date.from(instant));
        medicion.setCreador(entityManager.find(Asesor.class, idAsesor));
        if(tipo.compareTo("MENOR")==0){
            medicion.setTipo(Tipo.MENOR);
        }else if(tipo.compareTo("INTERMEDIA")==0){
            medicion.setTipo(Tipo.INTERMEDIA);
        }else{
            medicion.setTipo(Tipo.SUPERIOR);
        }
        entityManager.persist(medicion);
        return "redirect:/";
    }

    @GetMapping("/mediciones/listar_mediciones")
    public String verListarMedicionesString(Model model) {
            model.addAttribute("medicionesList", entityManager
            .createQuery("SELECT r FROM Medicion r", Medicion.class)
            .getResultList());

        return "mediciones/listar_mediciones";
    }

    @GetMapping("/asesores/annadir_asesor")
    public String annadirAsesor(Model model) {
        return "mediciones/annadir_asesor";
    }

    @GetMapping("/mediciones/inicio")
    public String irInicio(Model model) {
        return "inicio";
    }

    @Transactional
    @PostMapping("/asesores/annadir_asesor")
    public String annadirAsesor(Model model, @ModelAttribute Asesor asesor) {
        asesor.setActivo(true);
        entityManager.persist(asesor);
        return "inicio";
    }

    @Transactional
    @PostMapping("/mediciones/cambio_recibida")
    public String cambioRecibida(Model model, HttpSession session ,@RequestParam(name = "idCambio", required = true) Long idMedicion) {
        Medicion u=entityManager.find(Medicion.class, idMedicion);
        if(u.isRecibida()){
            u.setRecibida(false);
        }else{
            u.setRecibida(true);
        }
        return "redirect:/";
    }

    @Transactional
    @PostMapping("/mediciones/cambio_llamada")
    public String cambioLlamada(Model model, HttpSession session ,@RequestParam(name = "idCambio", required = true) Long idMedicion) {
        Medicion u=entityManager.find(Medicion.class, idMedicion);
        if(u.isLlamada()){
            u.setLlamada(false);
        }else{
            u.setLlamada(true);
        }
        return "redirect:/";
    }

    @Transactional
    @PostMapping("/mediciones/cambio_gestionada")
    public String cambioGestionada(Model model, HttpSession session ,@RequestParam(name = "idCambio", required = true) Long idMedicion) {
        Medicion u=entityManager.find(Medicion.class, idMedicion);
        if(u.isAtendida()){
            u.setAtendida(false);
        }else{
            u.setAtendida(true);
        }
        return "redirect:/";
    }

    @Transactional
    @PostMapping("/mediciones/eliminar_medicion")
    public String eliminarMedicion(Model model, HttpSession session ,@RequestParam(name = "idCambio", required = true) Long idMedicion) {
        Medicion u=entityManager.find(Medicion.class, idMedicion);
        entityManager.remove(u);
        return "redirect:/";
    }
  

    /*@GetMapping("/reviews/crear_review")
    public String mentoringListForReview(Model model) {
            model.addAttribute("mentoringList", entityManager
            .createQuery("SELECT m FROM Mentoring m", Mentoring.class)
            .getResultList());

        return "/reviews/crear_review";
    }*/

   /* @Transactional
    @GetMapping("/reviews/crear_review")
    public String crearReview(Model model) {
            entityManager.flush();
            model.addAttribute("mentoringList", entityManager
            .createQuery("SELECT m FROM Mentoring m", Mentoring.class)
            .getResultList());

        return "reviews/crear_review";
    }*/

 
/*
    @GetMapping("/{cosa}")
    public String unaCosa(@PathVariable String cosa, Model model) {
        return cosa;
    }
*/
}
