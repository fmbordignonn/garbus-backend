package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.entities.TrashesStatus;
import pucrs.ages.garbus.repositories.sql.TrashesSql;

import java.util.List;

@Repository
public interface TrashesStatusRepository extends JpaRepository<TrashesStatus, Long> {
}