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
@Table(name= "TRASHES_THRESHOLD")
public class TrashesThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "MAX_OCCUPATION")
    private double maxOcuppation;

    @Column(name = "COLOR")
    private String color;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_TRASH")
    private Trashes trashes;
}
