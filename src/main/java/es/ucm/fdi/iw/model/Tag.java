package es.ucm.fdi.iw.model;


import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class Tag {

    private long id;
    private String name;
    private String description;
    @ManyToMany
    private Mentoring mentoring;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }
}