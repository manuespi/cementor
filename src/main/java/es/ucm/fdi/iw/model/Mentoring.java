package es.ucm.fdi.iw.model;

import java.util.*;
import javax.persistence.*;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class Mentoring {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;
    @OneToOne
    private User mentor;
    @Column(name="aula")
    private String aula;
    @Column(name="name")
    private String name;
    @Column(name="date")
    private Date date;
    @ManyToMany
    private List<Tag> tags;

}
