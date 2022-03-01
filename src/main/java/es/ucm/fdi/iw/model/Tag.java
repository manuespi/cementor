package es.ucm.fdi.iw.model;

import java.util.List;
import javax.persistence.*;
@Entity
public class Tag {

    private long id;
    private String name;
    private String description;
    private List<Mentoring> mentoringList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public List<Mentoring> getMentorList(){
        return mentoringList;
    }
    public String getName(){
        return name;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setmentoringList(List<Mentoring> mentoringList){
        this.mentoringList =mentoringList;
    }
}