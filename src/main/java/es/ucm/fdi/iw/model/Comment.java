package es.ucm.fdi.iw.model;

import java.util.*;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
	private long id;
    
    @ManyToMany
    @JoinColumn(name="TAG_ID")
    private List<Tag> tag;
    private String text;
    
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

}
