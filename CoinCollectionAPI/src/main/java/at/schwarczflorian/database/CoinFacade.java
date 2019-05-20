package at.schwarczflorian.database;

import at.schwarczflorian.model.Coin;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class CoinFacade {

    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    static final String CONNECTION_STRING = "jdbc:derby://192.168.99.100:1521/XE;create=true";
    static final String USER = "dbu";
    static final String PASSWORD = "passme";
    private static Connection conn;

    @Inject
    SourceFacade sourceFacade;
    @Inject
    CurrencyFacade currencyFacade;
    @Inject
    CollectorFacade collectorFacade;

    @PostConstruct
    public void init() {
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

    public void close() {
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();
                System.out.println("Goodbye!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Coin coin) {
        try (PreparedStatement stmt = conn.prepareStatement("insert into COIN (VALUE, PRESERVATION, YEAROFCOINAGE, " +
                "FRONT, BACK, SOURCE_ID, CURRENCY_ID, COLLECTOR_ID) values (?, ?, ?, ?, ?, ?, ?, ?"))  {
            stmt.setInt(1, coin.getValue());
            stmt.setInt(2, coin.getPreservation());
            stmt.setInt(3, coin.getYearOfCoinage());


            stmt.setLong(6, coin.getSource().getId());
            stmt.setLong(7, coin.getCurrency().getId());
            stmt.setLong(8, coin.getCollector().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Coin> getAll() {
        List<Coin> coins = new LinkedList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select c from COIN c");
            while(rs.next()) {
                Coin coin = new Coin();
                coin.setValue(rs.getInt(1));
                coin.setPreservation(rs.getInt(2));
                coin.setYearOfCoinage(rs.getInt(3));
                coin.setSource(sourceFacade.getById(rs.getLong(6)));
                coin.setSource(currencyFacade.getById(rs.getLong(7)));
                coin.setSource(collectorFacade.getById(rs.getLong(8)));
                coins.add(coin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coins;
    }

    public Coin getById(Long id) {
        return null;
    }

}
