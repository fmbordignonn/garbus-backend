package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.TypesEvents;

import java.util.Date;


//Lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventsDTO {

    private long id;

    private String description;

    private Date creationDate;

    private char problemStatus;

    private TypesEvents typesEvents;

}