package es.ucm.fdi.iw.model;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="mensajeInterno")
public class MensajeInterno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne
    @JoinColumn(name="MEDICION_ID")
    private Medicion medicion;
    
}
