package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.Trashes;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesThresholdDTO {

    private long id;

    private double maxOcuppation;

    private String color;

}
