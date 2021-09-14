package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "EVENTS")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATION_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Column(name = "PROBLEM_STATUS")
    private char problemStatus;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_TYPE_EVENTS")
    private TypesEvents typesEvents;
}
