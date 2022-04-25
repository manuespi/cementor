package es.ucm.fdi.iw.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;    
    private String name;
    private String description;
    
    @ManyToMany
    @JoinColumn(name="COMMENT_ID")
    private List<Comment> comment;
    
    @ManyToMany
    @JoinColumn(name="MENTORING_ID")
    private List<Mentoring> mentoring;
}
