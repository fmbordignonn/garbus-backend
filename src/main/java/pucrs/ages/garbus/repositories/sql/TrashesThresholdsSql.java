package pucrs.ages.garbus.repositories.sql;

public class TrashesThresholdsSql {

    public static final String findAllThresholdsSql =
            "SELECT * FROM TRASHES_THRESHOLD t ORDER BY MAX_OCCUPATION";

    public static final String findThresholdsMaxOccupationByTrashIdSql =
            "SELECT * FROM TRASHES_THRESHOLD t WHERE LOWER(t.COLOR) = LOWER('YELLOW') and t.ID_TRASH = :trashId";

    public static final String findThresholdsByTrashIdSql =
            "SELECT t.ID, t.MAX_OCCUPATION, t.COLOR, t.ID_TRASH FROM TRASHES_THRESHOLD t WHERE t.ID_TRASH = :trashId ORDER BY MAX_OCCUPATION";
}
