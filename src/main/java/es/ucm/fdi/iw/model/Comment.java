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
     * @return List<Tag> return the tag
     */
    public List<Tag> getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(List<Tag> tag) {
        this.tag = tag;
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
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
