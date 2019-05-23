package at.schwarczflorian.database;

import at.schwarczflorian.model.Source;

import javax.ejb.Stateless;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class SourceFacade extends Facade {

    public void insert(Source source) {
        init();
        try (PreparedStatement stmt = conn.prepareStatement("insert into SOURCE (NAME) values (?)"))  {
            stmt.setString(1, source.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
    }

    public List<Source> getAll() {
        init();
        List<Source> sources = new LinkedList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select s.SOURCE_ID, s.NAME from SOURCE s");
            while(rs.next()) {
                Source source = new Source();
                source.setId(rs.getLong(1));
                source.setName(rs.getString(2));
                sources.add(source);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return sources;
    }

    public Source getById(Long id) {
        init();
        Source source = null;
        try (PreparedStatement stmt = conn.prepareStatement("select s.SOURCE_ID, s.NAME from SOURCE s where SOURCE_ID = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                source = new Source();
                source.setId(rs.getLong(1));
                source.setName(rs.getString(2));
            }
            if (rs.next()) {
                source = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return source;
    }

}
