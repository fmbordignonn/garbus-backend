package pucrs.ages.garbus.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;

//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZonesDTO {

    private long id;

    @NotEmpty
    private String name;

    private String description;

    private double longitude;

    private double latitude;

    private int buildingsCount;

    private int trashesCount;

}