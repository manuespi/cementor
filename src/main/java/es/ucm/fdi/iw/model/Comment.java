package es.ucm.fdi.iw.model;
import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;
    @OneToMany
    private Tag tag;
    private String text;
    @ManyToOne
    private User user;

}
