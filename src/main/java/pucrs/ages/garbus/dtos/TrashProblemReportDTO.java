package pucrs.ages.garbus.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashProblemReportDTO {
    @NotNull private Long trashId;
    private Long typeEventId;
    private String others;
}
