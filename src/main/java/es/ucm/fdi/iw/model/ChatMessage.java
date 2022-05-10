package es.ucm.fdi.iw.model;


import java.util.*;
import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "ChatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne
    //@JoinColumn(name = "MENTORING_ID")
    private User sender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")    
    private Date date;
    private long mentoringId;
    private String text;
}
