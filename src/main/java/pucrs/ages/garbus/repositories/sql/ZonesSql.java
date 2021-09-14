package pucrs.ages.garbus.repositories.sql;

public class ZonesSql {
    public final static String findZoneDescriptionByTrashIdSql =
            "SELECT DESCRIPTION FROM ZONES WHERE ZONES.ID = :zoneIdFromTrash";

    public final static String countTrashesByIdZone = ""
            + "SELECT COUNT(T.*) "
            + "FROM TRASHES T "
            + "JOIN ZONES Z "
            + "ON Z.ID = T.ID_ZONE "
            + "WHERE T.ID_ZONE = :zoneId";

    public final static String countBuildingsByIdZone = ""
            + "SELECT COUNT(B.*) "
            + "FROM BUILDINGS B "
            + "JOIN ZONES Z "
            + "ON Z.ID = B.ID_ZONE "
            + "WHERE B.ID_ZONE = :zoneId";
}
