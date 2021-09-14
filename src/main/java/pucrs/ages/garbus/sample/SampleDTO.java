package pucrs.ages.garbus.sample;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleDTO {

    private long id;

    @JsonProperty("create_date")
    private LocalDate date;

    private String description;

    private String name;

}