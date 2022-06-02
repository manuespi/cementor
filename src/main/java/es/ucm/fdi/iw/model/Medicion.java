package es.ucm.fdi.iw.model;


import java.util.*;
import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "Medicion")
public class Medicion {

    public enum Tipo {
        
        MENOR,
        INTERMEDIA,
        SUPERIOR
    }

    public enum Proveedor{
        ICARA,
        GALEO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    private int numero;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name="ASESOR_ID")
    private Asesor creador;

    private boolean recibida;
    private boolean llamada;
    private boolean atendida;

    @OneToMany
    private List<MensajeInterno> mensajes;

    private Tipo tipo;

}