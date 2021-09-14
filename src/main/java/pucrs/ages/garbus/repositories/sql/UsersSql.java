package pucrs.ages.garbus.repositories.sql;

public class UsersSql {
    public final static String findByLoginSql =
            "SELECT * FROM USERS WHERE LOWER(USERS.LOGIN) = LOWER(:login)";

    public final static String findByZoneSql =
            "SELECT * FROM USERS WHERE ID_ZONE = :zone";
}
