package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Events;

import java.util.List;

/**
 * JPA repository to Buildings entities.
 *
 */
@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
    List<Events> findEventsByProblemStatusEquals(char problemStatus);

    Events findEventsByProblemStatusEqualsAndTypesEventsId(char problemStatus, Long id);

    Events findEventsByTypesEventsId(Long id);
}