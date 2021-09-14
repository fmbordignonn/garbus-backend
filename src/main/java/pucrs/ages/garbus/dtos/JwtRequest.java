package pucrs.ages.garbus.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private String login;

    private String password;
}
