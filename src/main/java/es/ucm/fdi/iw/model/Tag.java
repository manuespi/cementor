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
    @Column(name="name")   
    private String name;
    @Column(name="description")
    private String description;
    @ManyToMany(targetEntity = Mentoring.class, mappedBy = "tags")
    private Mentoring mentoring;
}
