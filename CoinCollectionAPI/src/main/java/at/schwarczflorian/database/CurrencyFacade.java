package at.schwarczflorian.database;

import at.schwarczflorian.model.Currency;

import javax.ejb.Stateless;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class CurrencyFacade extends Facade{

    public void insert(Currency currency) {
        try (PreparedStatement stmt = conn.prepareStatement("insert into CURRENCY (NAME) values (?)"))  {
            stmt.setString(1, currency.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Currency> getAll() {
        List<Currency> currencies = new LinkedList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select c.CURRENCY_ID, c.NAME from CURRENCY c");
            while(rs.next()) {
                Currency currency = new Currency();
                currency.setId(rs.getLong(1));
                currency.setName(rs.getString(2));
                currencies.add(currency);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }

    public Currency getById(Long id) {
        Currency currency = null;
        try (PreparedStatement stmt = conn.prepareStatement("select c.CURRENCY_ID, c.NAME from CURRENCY c where CURRENCY_ID = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                currency = new Currency();
                currency.setId(rs.getLong(1));
                currency.setName(rs.getString(2));
            }
            if (rs.next()) {
                currency = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currency;
    }

}
