package es.ucm.fdi.iw.model;

@Entity
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
public void setId(long id) {
this.id = id;
}
public int getScore() {
return score;
}
public void setScore(int score) {
this.score = score;
}

public Profile getProfile() {
return profile;
}
public void setProfile(Profile profile) {
this.profile = profile;
}

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

public Mentoring getMentoring() {
return mentoring;
}

public void setMentoring(Mentoring mentoring) {
this.mentoring = mentoring;
}

}
