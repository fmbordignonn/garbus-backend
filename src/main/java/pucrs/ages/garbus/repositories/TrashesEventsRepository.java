package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.TrashesEvents;

import java.util.List;

/**
 * JPA repository to Buildings entities.
 *
 */
@Repository
public interface TrashesEventsRepository extends JpaRepository<TrashesEvents, Long> {
    @Modifying
    void deleteTrashesEventsByTrashesId(@Param("trashId") Long trashId);

    @Modifying
    void deleteTrashesEventsByUsersId(@Param("trashId") Long trashId);

    List<TrashesEvents> findAllByTrashesId(@Param("trashId") Long trashId);
}