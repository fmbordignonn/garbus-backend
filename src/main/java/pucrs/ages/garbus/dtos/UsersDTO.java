package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import pucrs.ages.garbus.entities.Profiles;
import pucrs.ages.garbus.entities.TypesEvents;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.entities.Zones;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


//Lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    public UsersDTO(Users users) {
        this.id = users.getId();
        this.email = users.getEmail();
        this.name = users.getName();
        this.login = users.getLogin();
        this.password = users.getPassword();
        this.registerDate = users.getRegisterDate();
        this.profile = users.getProfiles();
        this.zone = users.getZone();
        this.mustChangePwd = users.isMustChangePwd();
    }

    public static UsersDTO of(Users users) {
        if (users == null) return null;
        return new UsersDTO(users);
    }

    private long id;

    private String email;

    private String name;

    private String login;

    private boolean blocked;

    private Date registerDate;

    private Profiles profile;

    private String password;

    private Zones zone;

    private boolean mustChangePwd;

}