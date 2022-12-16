package stibride.repository;

import stibride.dto.FavoriteTripDto;
import stibride.exception.RepositoryException;
import stibride.jdbc.FavoriteTripsDao;
import java.util.List;

/**
 *
 * @author
 */
public class FavoriteTripsRepository implements Repository<Integer, FavoriteTripDto> {

    private final FavoriteTripsDao dao;

    public FavoriteTripsRepository() throws RepositoryException {
        dao = FavoriteTripsDao.getInstance();
    }

    @Override
    public List<FavoriteTripDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteTripDto get(Integer key) throws RepositoryException {
        FavoriteTripDto result = dao.select(key);
        return result;
    }

    public FavoriteTripDto get(String origin, String destination) throws RepositoryException {
        FavoriteTripDto result = dao.select(origin, destination);
        return result;
    }

    public FavoriteTripDto get(FavoriteTripDto item) throws RepositoryException {
        FavoriteTripDto result = dao.select(item);
        return result;
    }

    public Integer add(FavoriteTripDto item) throws RepositoryException {
        Integer key = item.getKey();
        if (!contains(item)) {
            key = dao.insert(item);
        }
        return key;
    }

    public void update(FavoriteTripDto item) throws RepositoryException {
        //Integer key = item.getKey();
        //if (contains(key) && !contains(item)) {
            dao.update(item);
        //}
    }

    public void delete(Integer key) throws RepositoryException {
        dao.delete(key);
    }

    public void delete(FavoriteTripDto item) throws RepositoryException {
        dao.delete(item);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        FavoriteTripDto results = dao.select(key);
        return results != null;
    }

    public boolean contains(FavoriteTripDto item) throws RepositoryException {
        FavoriteTripDto results = dao.select(item);
        return results != null;
    }

}