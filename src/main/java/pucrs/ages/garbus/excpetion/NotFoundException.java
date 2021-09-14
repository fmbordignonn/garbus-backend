package pucrs.ages.garbus.excpetion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pucrs.ages.garbus.dtos.ErrorResponse;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException {
    private ErrorResponse error;
}
