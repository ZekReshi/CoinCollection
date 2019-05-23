package at.schwarczflorian.database;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Stateless
abstract class Facade {

    private static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    static final String CONNECTION_STRING = "jdbc:derby://192.168.99.100:1521/XE;create=true";
    static final String USER = "dbu";
    static final String PASSWORD = "passme";
    static Connection conn;

    void init() {
        try {
            Class.forName(DRIVER_STRING);
            conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Verbindung zur Datenbank nicht möglich:\n"
                    + e.getMessage() + "\n");
        }
    }

    void close() {
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();
                System.out.println("Goodbye!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
