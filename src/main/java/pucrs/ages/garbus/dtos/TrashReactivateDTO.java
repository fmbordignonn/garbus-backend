package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TrashReactivateDTO {
    @NotNull
    private Long trashId;
}
