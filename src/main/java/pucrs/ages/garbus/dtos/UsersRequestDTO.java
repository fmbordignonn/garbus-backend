package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import pucrs.ages.garbus.entities.Profiles;
import pucrs.ages.garbus.entities.Zones;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDTO {

    private String email;

    private String name;

    private String login;

    private Date registerDate;

    private Profiles profile;

    private String password;

    private Zones zone;

    private boolean mustChangePwd;
}
