package pucrs.ages.garbus.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisableNotificationsRequest {
    int seconds;
}
