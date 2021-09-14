package pucrs.ages.garbus.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NOTIFICATION_TOKENS")
public class NotificationTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Autowired
    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private Users users;

    @Column(name = "TOKEN")
    private String token;

}
