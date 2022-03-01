package es.ucm.fdi.iw.model;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class Mentoring {
    private int id;
    @OneToOne
    private Profile mentor;
    private String aula;
    private String name;
    private Date date;
    @ManyToMany
    private Tag tags;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }

}
