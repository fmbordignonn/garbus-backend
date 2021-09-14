package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.*;

import java.util.List;


//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesAndBuildingsOnMapDTO {

    private List<TrashesReduceDTO> trashes;

    private List<BuildingsReduceDTO> buildings;

}