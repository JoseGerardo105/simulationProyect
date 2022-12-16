package stibride.presenter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import observer.Observable;
import observer.Observer;
import stibride.dto.FavoriteTripDto;
import stibride.exception.RepositoryException;
import stibride.model.Model;
import stibride.view.View;

/**
 * The presenter is the link between the view and the model. It is the presenter of the application in the MVP design patter.
 * 
 * @author
 */
public class Presenter implements Observer {

    private Model model;
    private View view;

    public Presenter(Model model, View view) throws RepositoryException, IOException {
        this.model = model;
        this.view = view;
        initialize();
    }

    /**
     * Initialize the view and model
     */
    public void initialize() throws RepositoryException, IOException {
        model.addObserver(this);
        view.initialize(this);
        initChoiceBox();
        initFavoriteTripsChoiceBox();
    }

    /**
     * Initialize the choice box with the list of stations
     */
    private void initChoiceBox() throws RepositoryException, IOException {
        List<String> stations = model.getStations();
        view.initChoiceBox(stations);
    }

    /**
     * Calls the `search` function on the `model` object
     * 
     * @param origin the id of the origin station
     * @param destination the id of the destination station
     */
    public void doResearch(int origin, int destination) {
        model.search(origin, destination);
    }

    /**
     * Calls the `search` function on the `model` object
     * 
     * @param origin the title of the origin station
     * @param destination the title of the destination station
     */
    public void doResearch(String origin, String destination) {
        model.search(origin, destination);
    }

    /**
     * Initialize the favorite trips choice box with the favorite trips from the model
     */
    private void initFavoriteTripsChoiceBox() throws RepositoryException, IOException {
        List<FavoriteTripDto> favoriteTrips = model.getFavoriteTrips();
        view.initFavoriteTripsChoiceBox(favoriteTrips);
    }

    /**
     * Add a favorite trip to the model
     * 
     * @param origin the origin of the trip
     * @param destination the destination of the trip
     */
    public void addFavoriteTrip(String origin, String destination) throws RepositoryException, IOException {
        model.addFavoriteTrip(origin, destination);
        initFavoriteTripsChoiceBox();
    }

    /**
     * Update the favorite trip in the model and update the favorite trips choice box
     * 
     * @param key The primary key of the favorite trip to update.
     * @param origin the origin of the trip
     * @param destination the destination of the trip
     */
    public void updateFavoriteTrip(int key, String origin, String destination) throws RepositoryException, IOException {
        model.updateFavoriteTrip(key, origin, destination);
        initFavoriteTripsChoiceBox();
    }
    
    /**
     * Delete a favorite trip from the database and update the favorite trips choice box
     * 
     * @param trip The trip to be deleted.
     */
    public void deleteFavoriteTrip(FavoriteTripDto trip) throws RepositoryException, IOException {
        model.deleteFavoriteTrip(trip);
        initFavoriteTripsChoiceBox();
    }

    /**
     * Update the table view with the search results
     * 
     * @param observable The object that is being observed.
     * @param arg The argument passed to the update method.
     */
    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("");
        Model savedModel = (Model) observable;
        try {
            view.updateTableView(savedModel.getSearchResult());
        } catch (IOException ex) {
            Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
