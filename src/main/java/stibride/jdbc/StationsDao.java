package stibride.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import stibride.dto.StationsDto;
import stibride.exception.RepositoryException;
import stibride.repository.Dao;

/**
 *
 * @author
 */
public class StationsDao implements Dao<Integer, StationsDto> {

    private Connection connexion;

    private StationsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static StationsDao getInstance() throws RepositoryException {
        return StationsDaoHolder.getInstance();
    }

    @Override
    public List<StationsDto> selectAll() throws RepositoryException {
        String sql = "SELECT id, name FROM STATIONS "
                + "ORDER BY id;";
        List<StationsDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StationsDto dto = new StationsDto(rs.getInt(1), rs.getString(2));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StationsDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No hay clave dada en el parámetroe");
        }
        String sql = "SELECT id, name FROM STATIONS "
                + "WHERE id = ?;";

        StationsDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationsDto(rs.getInt(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Registro no único" + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    // @Override
    public StationsDto select(String key) throws RepositoryException {
        if (key == null || key == "") {
            throw new RepositoryException("No hay clave dada en el parámetro");
        }
        String sql = "SELECT id, name, FROM STATIONS "
                + "WHERE name = ?;";

        StationsDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationsDto(rs.getInt(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Registro no único " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private static class StationsDaoHolder {

        private static StationsDao getInstance() throws RepositoryException {
            return new StationsDao();
        }
    }

}
