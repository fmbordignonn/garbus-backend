package pucrs.ages.garbus.dtos;

import lombok.*;

//Lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashReportDTO {
    long id;
    double occupation;
}
