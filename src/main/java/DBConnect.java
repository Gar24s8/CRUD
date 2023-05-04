import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static String dbURL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'";
    private static String dbUsername = "sa";
    private static String dbPassword = "";
    private static Connection connection = null;

    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}