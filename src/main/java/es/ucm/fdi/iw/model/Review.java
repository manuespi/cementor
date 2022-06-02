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

@ManyToOne
@JoinColumn(name="CREATOR_ID")
private User creator;

@ManyToOne
@JoinColumn(name="MENTORING_ID")
private Mentoring mentoring;

private String text;

private Integer score;






}
