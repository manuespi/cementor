package es.ucm.fdi.iw.model;
import java.util.*;
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
    @ManyToOne
    private Comment comment;
    @ManyToMany
    private List<Mentoring> mentoring;
}
