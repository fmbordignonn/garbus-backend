package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.repositories.sql.TrashesSql;

import java.util.List;

@Repository
public interface TrashesRepository extends JpaRepository<Trashes, Long> {
    @Query(
            value = TrashesSql.findByZonesIdAndBuildingsInZonesIdSQL,
            nativeQuery = true
    )
    List<Trashes> findByZonesIdAndBuildingsInZonesId(@Param("zoneId") Long zoneId);

    @Query(
            value = TrashesSql.findByBuildingIdSQL,
            nativeQuery = true
    )
    List<Trashes> findByBuildingId(@Param("buildingId") Long buildingId);

    @Query(
            value = TrashesSql.findTrashByTrashIdSql,
            nativeQuery = true
    )
    Trashes findByTrashId(@Param("trashId") Long trashId);

    @Query(
            value = TrashesSql.findTrashByStatusId,
            nativeQuery = true
    )
    List<Trashes> findByStatusId(@Param("statusId") Long statusId);

}