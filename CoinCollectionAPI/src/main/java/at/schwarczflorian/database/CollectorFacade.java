package at.schwarczflorian.database;

import at.schwarczflorian.model.Collector;

import javax.ejb.Stateless;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class CollectorFacade extends Facade {

    public void insert(Collector collector) {
        init();
        try (PreparedStatement stmt = conn.prepareStatement("insert into COLLECTOR (FIRSTNAME, LASTNAME) values (?, ?)"))  {
            stmt.setString(1, collector.getFirstName());
            stmt.setString(2, collector.getLastName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    public List<Collector> getAll() {
        init();
        List<Collector> collectors = new LinkedList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select c.COLLECTOR_ID, c.FIRSTNAME, c.LASTNAME from COLLECTOR c");
            while(rs.next()) {
                Collector collector = new Collector();
                collector.setId(rs.getLong(1));
                collector.setFirstName(rs.getString(2));
                collector.setLastName(rs.getString(3));
                collectors.add(collector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return collectors;
    }

    public Collector getById(Long id) {
        init();
        Collector collector = null;
        try (PreparedStatement stmt = conn.prepareStatement("select c.COLLECTOR_ID, c.FIRSTNAME, c.LASTNAME from COLLECTOR c where COLLECTOR_ID = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                collector = new Collector();
                collector.setId(rs.getLong(1));
                collector.setFirstName(rs.getString(2));
                collector.setLastName(rs.getString(3));
            }
            if (rs.next()) {
                collector = null;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return collector;
    }

}
