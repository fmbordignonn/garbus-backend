package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.TrashesStatus;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashDetailsDTO {

    private String trashDescription;

    private double capacity;

    private double occupation;

    private TrashesStatus trashesStatus;

    private List<TrashesThresholdDTO> trashesThreshold;

    private String localDescription;

}
