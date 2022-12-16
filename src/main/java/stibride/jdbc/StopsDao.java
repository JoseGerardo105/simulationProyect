package stibride.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import stibride.dto.StopsDto;
import stibride.exception.RepositoryException;
import stibride.repository.Dao;

/**
 *
 * @author
 */
public class StopsDao implements Dao<Integer, StopsDto> {

    private Connection connexion;

    private StopsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();

    }

    public static StopsDao getInstance() throws RepositoryException {
        return StopsDaoHolder.getInstance();
    }
    
     @Override
    public List<StopsDto> selectAll() throws RepositoryException {
        String sql = "SELECT id_order, id_station, id_line FROM STOPS "
                + "ORDER BY id_line, id_order;";
        List<StopsDto> dtos = new ArrayList<>();
        try ( Statement stmt = connexion.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StopsDto dto = new StopsDto(rs.getInt(1), rs.getInt(2));
                dto.addLine(rs.getInt(3));
                
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }


    @Override
    public StopsDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No hay clave dada en el parámetro");
        }
        String sql = "SELECT id_order, stations.name, id_line FROM STOPS "
                + "JOIN STATIONS ON id_station = stations.id "
                + "WHERE id_station = ?;";

        StopsDto dto = null;
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (dto == null) {
                    dto = new StopsDto(rs.getInt(1), rs.getString(2));
                }
                dto.addLine(rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    //@Override
    public StopsDto select(String key) throws RepositoryException {
        if (key == null || key == "") {
            throw new RepositoryException("No hay clave dada en el parámetro");
        }
        String sql = "SELECT id_order, stations.name, id_line FROM STOPS "
                + "JOIN STATIONS ON id_station = stations.id "
                + "WHERE stations.name = ?;";

        StopsDto dto = null;
        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (dto == null) {
                    dto = new StopsDto(rs.getInt(1), rs.getString(2));
                }
                dto.addLine(rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private static class StopsDaoHolder {

        private static StopsDao getInstance() throws RepositoryException {
            return new StopsDao();
        }
    }
}
