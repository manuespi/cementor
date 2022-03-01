package es.ucm.fdi.iw.model;

@Entity
@Data
public class Review {

private long id;
private int score;
private Profile profile;
private String text;
private Mentoring mentoring;

@Id
@GeneratedValueue(strategy = GenerationType.IDENTITY)
public long getId() {
return id;
}

}
