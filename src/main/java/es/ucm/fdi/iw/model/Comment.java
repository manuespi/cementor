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
    @OneToMany(targetEntity = Tag.class)
    @JoinColumn(name ="tag_id")
    private Tag tag;
    @Column(name="text")
    private String text;
    @OneToOne
    private User user;

}
