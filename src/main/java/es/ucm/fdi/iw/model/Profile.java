package es.ucm.fdi.iw.model;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
@Data
public class Profile {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
@SequenceGenerator(name = "gen", sequenceName = "gen")
private long id;
private String name;
private String role;
private List<Mentoring> mentoringList;
private String description;
private List<Review> reviewList;
private String photo;
private int totalScore;
private int timesReviewed;


}
