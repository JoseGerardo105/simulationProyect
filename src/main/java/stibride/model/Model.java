package stibride.model;

import java.util.List;

import observer.Observer;
import stibride.dto.FavoriteTripDto;
import stibride.dto.StationsDto;

/**
 * The model of the application in the MVP design patter.
 *  
 * @author
 */
public interface Model {

    /**
     * Get the metro network
     * 
     * @return The network object.
     */
    public Network getNetwork();

    /**
     * Get a list of all the stations name in the system
     * 
     * @return A list of strings.
     */
    public List<String> getStations();

    /**
     * Given an origin and destination, find the shortest path between them
     * 
     * @param idOrigin the id of the origin station
     * @param idDestination the id of the destination station
     */
    public void search(int idOrigin, int idDestination);

    /**
     * Search for the shortest path between origin and destination
     * 
     * @param idOrigin the name of the origin station
     * @param idDestination the name of the destination station
     */
    public void search(String origin, String destination);

    /**
     * Get the list of stations that match the search criteria
     * 
     * @return A list of stations.
     */
    public List<StationsDto> getSearchResult();

    /**
     * Get a list of favorite trips
     * 
     * @return A list of favorite trips.
     */
    public List<FavoriteTripDto> getFavoriteTrips();

    /**
     * Add a favorite trip to the database
     * 
     * @param origin The origin of the trip.
     * @param destination the name of the destination
     */
    public void addFavoriteTrip(String origin, String destination);

    /**
     * Update the favorite trip for a user
     * 
     * @param key The unique identifier for the trip.
     * @param origin The origin of the trip.
     * @param destination the destination of the trip
     */
    public void updateFavoriteTrip(int key, String origin, String destination);

    /**
     * Delete a favorite trip
     * 
     * @param trip The trip to be deleted.
     */
    public void deleteFavoriteTrip(FavoriteTripDto trip);

    /**
     * Add an observer to the list of observers
     * 
     * @param observer The observer to be added.
     */
    public void addObserver(Observer observer);
}
