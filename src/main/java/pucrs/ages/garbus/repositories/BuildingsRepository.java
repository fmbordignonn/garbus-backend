package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Buildings;
import pucrs.ages.garbus.repositories.sql.BuildingsSql;

/**
 * JPA repository to Buildings entities.
 */
@Repository
public interface BuildingsRepository extends JpaRepository<Buildings, Long> {
    @Query(
            value = BuildingsSql.findBuildingNameByTrashId,
            nativeQuery = true
    )
    String findBuildingNameByTrashId(@Param("buildingIdFromTrash") Long buildingIdFromTrash);
}