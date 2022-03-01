package es.ucm.fdi.iw.model;
import javax.persistence.*;

import lombok.Data;
@Entity
@Data
public class Comment {

    private long id;
    @OneToMany
    private Tag tag;
    private String text;
    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }

}
