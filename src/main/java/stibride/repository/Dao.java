package stibride.repository;

import stibride.dto.Dto;
import stibride.exception.RepositoryException;
import java.util.List;

/**
 * Data access object of a resource (file, database, web service).
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Data_access_object"> Wikipedia</a>
 *
 * @author
 * @param <K> key of an item.
 * @param <T> item of the resource.
 */
public interface Dao<K, T extends Dto<K>> {

    /**
     * Returns all the elements of the resource. This method can be long.
     *
     * @return all the elements of the resource.
     * @throws RepositoryException if the resource can't be accessed.
     */
    List<T> selectAll() throws RepositoryException;

    /**
     * Returns an element based on its key.
     *
     * @param key key of the element to select.
     * @return an element based on its key.
     * @throws RepositoryException if the resource can't be accessed.
     */
    T select(K key) throws RepositoryException;

    //T select(String key) throws RepositoryException;

}
