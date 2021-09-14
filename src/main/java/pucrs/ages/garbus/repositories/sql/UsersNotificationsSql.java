package pucrs.ages.garbus.repositories.sql;

public class UsersNotificationsSql {
    public final static String findByUserId =
            "SELECT * FROM USERS_NOTIFICATIONS WHERE ID_USER = :userId";

    public final static String findByLogin =
            "SELECT N.* "
                    + "FROM USERS_NOTIFICATIONS N "
                    + "JOIN USERS U "
                    + "ON U.ID = N.ID_USER "
                    + "WHERE LOWER(U.LOGIN) = LOWER(:login)";
}
