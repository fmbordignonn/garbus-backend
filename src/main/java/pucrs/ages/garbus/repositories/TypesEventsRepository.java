package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.TypesEvents;

/**
 * JPA repository to Buildings entities.
 *
 */
@Repository
public interface TypesEventsRepository extends JpaRepository<TypesEvents, Long> {
}