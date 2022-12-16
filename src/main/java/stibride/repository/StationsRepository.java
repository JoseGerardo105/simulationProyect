/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stibride.repository;

import stibride.dto.StationsDto;
import stibride.exception.RepositoryException;
import stibride.jdbc.StationsDao;
import java.util.List;

/**
 *
 * @author
 */
public class StationsRepository implements Repository<Integer, StationsDto> {

    private final StationsDao dao;

    public StationsRepository() throws RepositoryException {
        dao = StationsDao.getInstance();
    }

    StationsRepository(StationsDao dao) {
        this.dao = dao;
    }

    @Override
    public List<StationsDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StationsDto get(Integer key) throws RepositoryException {
        return dao.select(key);
    }

    //@Override
    public StationsDto get(String stationName) throws RepositoryException {
        return dao.select(stationName);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        var results = dao.select(key);
        return results != null;
    }

    //@Override
    public boolean contains(String stationName) throws RepositoryException {
        var results = dao.select(stationName);
        return results != null;
    }

}
