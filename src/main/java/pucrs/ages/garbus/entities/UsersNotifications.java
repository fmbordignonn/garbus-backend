package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS_NOTIFICATIONS")
public class UsersNotifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_USER")
    private Users users;

    @Column(name = "DISABLED_UNTIL")
    @DateTimeFormat
    private LocalDateTime disabledUntil;
}
