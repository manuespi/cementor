package es.ucm.fdi.iw.model;

import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;    
    private String name;
    private String description;
    @ManyToMany
    private Mentoring mentoring;
}
