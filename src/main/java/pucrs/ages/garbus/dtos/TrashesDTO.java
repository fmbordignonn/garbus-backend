package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.TrashesStatus;
import pucrs.ages.garbus.entities.Buildings;
import pucrs.ages.garbus.entities.TrashType;
import pucrs.ages.garbus.entities.Zones;

import java.util.List;


//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesDTO {

    private long trashId;

    private String brand;

    private String trashDescription;

    private double capacity;

    private double occupation;

    private double longitude;

    private double latitude;

    private String localDescription;

    private TrashesStatus trashesStatus;

    private TrashType trashType;

    private List<TrashesThresholdDTO> trashesThreshold;

    private Buildings building;

    private Zones zone;

}
//    t.id, t.brand, t.description, t.capacity, t.occupation, t.trashesStatus, t.trashType, t.buildings, t.zones