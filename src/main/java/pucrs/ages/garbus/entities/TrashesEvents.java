package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "TRASHES_EVENTS")
public class TrashesEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_EVENT")
    private Events events;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_TRASH")
    private Trashes trashes;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_USER")
    private Users users;

    @Column(name = "OCCUPATION")
    private double occupation;

    @Column(name = "OTHERS")
    private String others;

    @Column(name = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

}
