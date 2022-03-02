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

@Column(name="SCORE")
private int score;

@ManyToOne
@JoinColumn(name="CREATOR_ID")
private User creator;

@Column(name="TEXT")
private String text;

@ManyToOne
@JoinColumn(name="MENTORING_ID")
private Mentoring mentoring;

}
