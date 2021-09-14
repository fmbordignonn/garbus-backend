package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.NotificationTokens;

/**
 * JPA repository to UserNotifications entities.
 */
@Repository
public interface NotificationTokensRepository extends JpaRepository<NotificationTokens, Long> {
    NotificationTokens findByToken(@Param("token") String token);

    NotificationTokens findByUsersId(@Param("id") int userId);
}
