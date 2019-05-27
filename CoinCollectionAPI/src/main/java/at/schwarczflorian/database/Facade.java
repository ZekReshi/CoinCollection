package at.schwarczflorian.database;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Stateless
abstract class Facade {

    static Connection conn;

    @PostConstruct
    void init() {
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/oracledbDS");
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Verbindung zur Datenbank nicht möglich:\n"
                    + e.getMessage() + "\n");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Goodbye!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
