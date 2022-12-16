package stibride.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import stibride.dto.FavoriteTripDto;
import stibride.exception.RepositoryException;
import stibride.repository.Dao;

/**
 *
 * @author
 */
public class FavoriteTripsDao implements Dao<Integer, FavoriteTripDto> {

    private final Connection connexion;

    public FavoriteTripsDao() throws RepositoryException {
        this.connexion = DBManager.getInstance().getConnection();
    }

    public static FavoriteTripsDao getInstance() throws RepositoryException {
        return favorisDaoHolder.getInstance();
    }

    @Override
    public List<FavoriteTripDto> selectAll() throws RepositoryException {
        String query = "SELECT * FROM FAVORITESROUTES";

        List<FavoriteTripDto> dtos = new ArrayList<>();
        try (Statement statement = connexion.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                FavoriteTripDto favoris = new FavoriteTripDto(result.getInt(1),
                        result.getString(2), result.getString(3));
                dtos.add(favoris);
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return dtos;

    }

    public FavoriteTripDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No hay clave dada en el parámetro");
        }
        String query = "SELECT id, origin, destination FROM FAVORITESROUTES "
                + "WHERE id = ?";

        FavoriteTripDto route = null;
        try (PreparedStatement PrepaStatement = connexion.prepareStatement(query)) {
            PrepaStatement.setInt(1, key);
            ResultSet rs = PrepaStatement.executeQuery();

            int count = 0;
            while (rs.next()) {
                route = new FavoriteTripDto(rs.getInt(0), rs.getString(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Record pas unique " + key);
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return route;
    }

    public FavoriteTripDto select(FavoriteTripDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("No hay clave dada en el parámetro");
        }
        String query = "SELECT id, origin, destination FROM FAVORITESROUTES "
                + "WHERE origin=? AND destination=? ";

        FavoriteTripDto route = null;
        try (PreparedStatement PrepaStatement = connexion.prepareStatement(query)) {
            PrepaStatement.setString(1, item.getOrigin());
            PrepaStatement.setString(2, item.getDestination());
            ResultSet rs = PrepaStatement.executeQuery();

            int count = 0;
            while (rs.next()) {
                route = new FavoriteTripDto(rs.getInt(0), rs.getString(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Registro no único " + item.toString());
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return route;
    }

    public FavoriteTripDto select(String origin, String destination) throws RepositoryException {
        if (origin == null || destination == null) {
            throw new RepositoryException("No hay clave dada en el parámetroe");
        }
        String query = "SELECT id, origin, destination FROM FAVORITESROUTES "
                + "WHERE origin=? destination=?";

        FavoriteTripDto route = null;
        try (PreparedStatement PrepaStatement = connexion.prepareStatement(query)) {
            PrepaStatement.setString(1, origin);
            PrepaStatement.setString(2, destination);
            ResultSet result = PrepaStatement.executeQuery();

            int count = 0;
            while (result.next()) {
                route = new FavoriteTripDto(result.getInt(0), result.getString(1), result.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Registro no único " + origin + " -> " + destination);
            }

        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return route;
    }

    // @Override
    public Integer insert(FavoriteTripDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("No hay clave dada en el parámetro");
        }

        Integer id = 0;
        String sql = "INSERT INTO FAVORITESROUTES(origin, destination) VALUES (?, ?)";

        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getOrigin());
            pstmt.setString(2, item.getDestination());
            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return id;
    }

    public Integer insert(String origin, String destination) throws RepositoryException {
        if (origin == null || destination == null) {
            throw new RepositoryException("No hay clave dada en el parámetro");
        }

        Integer id = 0;
        String sql = "INSERT INTO FAVORITESROUTES(origin, destination) VALUES (?, ?)";

        try (PreparedStatement prep = connexion.prepareStatement(sql)) {
            prep.setString(1, origin);
            prep.setString(2, destination);
            prep.executeUpdate();
            ResultSet result = prep.getGeneratedKeys();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
        return id;
    }

    public void update(FavoriteTripDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("No hay clave dada en el parámetro");
        }
        String query = "UPDATE FAVORITESROUTES SET origin=?, destination=? where id=?";
        try (PreparedStatement prep = connexion.prepareStatement(query)) {
            prep.setString(1, item.getOrigin());
            prep.setString(2, item.getDestination());
            prep.setInt(3, item.getKey());

            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    public void delete(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("La cle est nulle");
        }

        String query = "DELETE FROM FAVORITESROUTES WHERE id =?";

        try (PreparedStatement prep = connexion.prepareStatement(query)) {
            prep.setInt(1, key);
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    /**
     * Deletes the item of the specific key from the FavoritesDto.
     *
     * @param key of the element to delete
     * @throws RepositoryException if the FavoritesDto can't be accessed
     */
    public void delete(FavoriteTripDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("L' item est null");
        }

        String query = "DELETE FROM FAVORITESROUTES WHERE id=? AND origin=? AND destination=?";

        try (PreparedStatement prep = connexion.prepareStatement(query)) {
            prep.setInt(1, item.getKey());
            prep.setString(2, item.getOrigin());
            prep.setString(3, item.getDestination());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException(ex);
        }
    }

    private static class favorisDaoHolder {

        private static FavoriteTripsDao getInstance() throws RepositoryException {
            return new FavoriteTripsDao();
        }

    }

}
