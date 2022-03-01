package es.ucm.fdi.iw.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Mentoring {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
@SequenceGenerator(name = "gen", sequenceName = "gen")
private int id;

@OneToOne
private Profile mentor;

private String aula;
private String name;
private Date date;

@OneToMany
private List<Tag> tagList;

}
