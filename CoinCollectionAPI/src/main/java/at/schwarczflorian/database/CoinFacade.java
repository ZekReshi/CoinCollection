package at.schwarczflorian.database;

import at.schwarczflorian.model.Coin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class CoinFacade extends Facade {

    @Inject
    SourceFacade sourceFacade;
    @Inject
    CurrencyFacade currencyFacade;
    @Inject
    CollectorFacade collectorFacade;

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
            ResultSet rs = stmt.executeQuery("select c.COIN_ID, c.VALUE, c.PRESERVATION, c.YEAROFCOINAGE, " +
                    "c.SOURCE_ID, c.CURRENCY_ID, c.COLLECTOR_ID from COIN c");
            while(rs.next()) {
                Coin coin = generateCoin(rs);
                coins.add(coin);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coins;
    }

    public Coin getById(Long id) {
        Coin coin = null;
        try (PreparedStatement stmt = conn.prepareStatement("select c.COIN_ID, c.VALUE, c.PRESERVATION, " +
                "c.YEAROFCOINAGE, c.SOURCE_ID, c.CURRENCY_ID, c.COLLECTOR_ID from COIN c where COIN_ID = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                coin = generateCoin(rs);
            }
            if (rs.next()) {
                coin = null;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coin;
    }

    public BufferedImage getFront(Long id) {
        return null;
    }

    public void setFront(Long id, ByteArrayInputStream bais) {
        try (PreparedStatement stmt = conn.prepareStatement("update table COIN set FRONT = ? where COIN_ID = ?")) {
            stmt.setBinaryStream(1, bais);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getBack(Long id) {
        return null;
    }

    public void setBack(Long id, ByteArrayInputStream bais) {
        try (PreparedStatement stmt = conn.prepareStatement("update table COIN set BACK = ? where COIN_ID = ?")) {
            stmt.setBinaryStream(1, bais);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Coin generateCoin(ResultSet rs) throws SQLException {
        Coin coin = new Coin();
        coin.setId(rs.getLong(1));
        coin.setValue(rs.getInt(2));
        coin.setPreservation(rs.getInt(3));
        coin.setYearOfCoinage(rs.getInt(4));
        coin.setSource(sourceFacade.getById(rs.getLong(7)));
        coin.setCurrency(currencyFacade.getById(rs.getLong(8)));
        coin.setCollector(collectorFacade.getById(rs.getLong(9)));
        return coin;
    }

}
