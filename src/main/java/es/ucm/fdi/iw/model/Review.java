package es.ucm.fdi.iw.model;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="review")
public class Review {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
@SequenceGenerator(name = "gen", sequenceName = "gen")
private long id;
private Integer score;

@ManyToOne
@JoinColumn(name="CREATOR_ID")
private User creator;
private String text;

@ManyToOne
@JoinColumn(name="MENTORING_ID")
private Mentoring mentoring;


}
