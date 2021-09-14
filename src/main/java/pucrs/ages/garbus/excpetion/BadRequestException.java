package pucrs.ages.garbus.excpetion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pucrs.ages.garbus.dtos.ErrorResponse;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private ErrorResponse error;
}
