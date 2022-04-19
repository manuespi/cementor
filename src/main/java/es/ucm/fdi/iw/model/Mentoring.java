package es.ucm.fdi.iw.model;


import java.util.*;
import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "mentoring")
public class Mentoring {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne
    @JoinColumn(name = "MENTOR_ID")
    private User mentor;
    private String classroom;   
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")    
    private Date date;
    private String ini;
    private String fin;


    @ManyToMany
    @JoinColumn(name = "TAG_ID")
    private List<Tag> tag;
    /* añadir a list review de la mentoria */
    @OneToMany
    private List<Review> reviews;


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
     * @return User return the mentor
     */
    public User getMentor() {
        return mentor;
    }

    /**
     * @param mentor the mentor to set
     */
    public void setMentor(User mentor) {
        this.mentor = mentor;
    }

    /**
     * @return String return the classroom
     */
    public String getClassroom() {
        return classroom;
    }

    /**
     * @param classroom the classroom to set
     */
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return String return the ini
     */
    public String getIni() {
        return ini;
    }

    /**
     * @param ini the ini to set
     */
    public void setIni(String ini) {
        this.ini = ini;
    }

    /**
     * @return String return the fin
     */
    public String getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(String fin) {
        this.fin = fin;
    }

    /**
     * @return List<Tag> return the tags
     */
    public List<Tag> getTags() {
        return tag;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<Tag> tag) {
        this.tag = tag;
    }

    /**
     * @return List<Review> return the reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
