package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryResponse {

    private boolean hasEmail;
    private boolean emailSent;

}
