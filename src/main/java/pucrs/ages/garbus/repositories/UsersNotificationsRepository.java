package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.UsersNotifications;
import pucrs.ages.garbus.repositories.sql.UsersNotificationsSql;

import java.util.Optional;

/**
 * JPA repository to UserNotifications entities.
 */
@Repository
public interface UsersNotificationsRepository extends JpaRepository<UsersNotifications, Long> {
    @Query(
            value = UsersNotificationsSql.findByUserId,
            nativeQuery = true
    )
    UsersNotifications findByUserId(@Param("userId") Long userId);

    @Query(
            value = UsersNotificationsSql.findByLogin,
            nativeQuery = true
    )
    Optional<UsersNotifications> findByLogin(@Param("login") String login);
}
