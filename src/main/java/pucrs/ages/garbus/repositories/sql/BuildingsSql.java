package pucrs.ages.garbus.repositories.sql;

public class BuildingsSql {
    public final static String findBuildingNameByTrashId =
            "SELECT NAME FROM BUILDINGS WHERE BUILDINGS.ID = :buildingIdFromTrash";
}
