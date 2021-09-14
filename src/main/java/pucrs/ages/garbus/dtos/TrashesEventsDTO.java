package pucrs.ages.garbus.dtos;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import pucrs.ages.garbus.entities.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;


//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesEventsDTO {

    private long id;

    private Events event;

//    private Trashes trashes;

    private String login;

    private double occupation;

    private String others;

    private Date date;

}