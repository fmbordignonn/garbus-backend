package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationsDisabledUntilWhenDTO {
    private LocalDateTime disabledUntil;
}
