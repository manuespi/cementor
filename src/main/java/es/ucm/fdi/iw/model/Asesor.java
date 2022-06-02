package es.ucm.fdi.iw.model;


import java.util.*;
import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "Asesor")
public class Asesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    private String nombre;
    private Boolean activo;

    @OneToMany(targetEntity=Medicion.class)
    List<Medicion> mediciones; 

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
