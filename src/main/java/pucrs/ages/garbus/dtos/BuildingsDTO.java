package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.Zones;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildingsDTO {

    private long id;

    private String name;

    private Zones zones;

    private Long trashesCount;

    private double longitude;

    private double latitude;

}
