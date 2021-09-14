package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Trashes")
public class Trashes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "BRAND")
    private String brand;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CAPACITY")
    private double capacity;

    @Column(name = "OCCUPATION")
    private double occupation;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LOCAL_DESCRIPTION")
    private String localDescription;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_STATUS")
    private TrashesStatus trashesStatus;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_TYPE")
    private TrashType trashType;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_BUILDING")
    private Buildings buildings;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_ZONE")
    private Zones zones;

}