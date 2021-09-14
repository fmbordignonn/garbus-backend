package pucrs.ages.garbus.dtos;

import lombok.*;

//Lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypesEventsDTO {

    private long id;

    private String name;

    private String description;

}