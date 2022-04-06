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


    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Integer return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return User return the creator
     */
    public User getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * @return String return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return Mentoring return the mentoring
     */
    public Mentoring getMentoring() {
        return mentoring;
    }

    /**
     * @param mentoring the mentoring to set
     */
    public void setMentoring(Mentoring mentoring) {
        this.mentoring = mentoring;
    }

}
