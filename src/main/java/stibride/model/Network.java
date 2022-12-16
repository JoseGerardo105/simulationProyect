package stibride.model;

import java.io.IOException;
import java.util.List;

import stibride.config.ConfigManager;
import stibride.dto.StationsDto;
import stibride.dto.StopsDto;
import stibride.exception.RepositoryException;
import stibride.repository.StationsRepository;
import stibride.repository.StopsRepository;

/**
 *
 * @author
 */
/**
 * The Network class creates a graph of stations
 * 
 * @author Ayoub
 */
public class Network {

    private StationGraph graphStations;

    public Network(StationGraph graphStations) {
        this.graphStations = graphStations;
    }

    public Network() throws RepositoryException {
        this.graphStations = new StationGraph();
        initGraph();
        initDestinations();
    }


    public void initGraph() throws RepositoryException {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos: " + dbUrl);

            StationsRepository repository = new StationsRepository();
            List<StationsDto> dtos = repository.getAll();

            for (StationsDto dto : dtos) {
                graphStations.addNode(new StationNode(dto));
            }

        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error Repository " + ex.getMessage());
        }
    }


    private void initDestinations() {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos: " + dbUrl);

            StopsRepository repository = new StopsRepository();
            List<StopsDto> dtos = repository.getAll();

            for (int i = 0; i < dtos.size(); i++) {
                if (i == dtos.size() - 1) {
                    break;
                }

                int originStationKey = dtos.get(i).getStationKey();
                int originLine = dtos.get(i).getLine();
                int originOrder = dtos.get(i).getKey();

                int destinationStationKey = dtos.get(i + 1).getStationKey();
                int destinationLine = dtos.get(i + 1).getLine();
                int destinationOrder = dtos.get(i + 1).getKey();

                if (originLine == destinationLine && originOrder == destinationOrder - 1) {
                    addDestination(originStationKey, destinationStationKey);
                }
            }

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error Repository " + ex.getMessage());
        }
    }

    /**
     * Add a destination to the graph
     * 
     * @param originStationKey The station key of the origin station.
     * @param destinationStationKey The station key of the destination station.
     */
    private void addDestination(int originStationKey, int destinationStationKey) {
        StationNode originNode = graphStations.search(originStationKey);
        StationNode destinationNode = graphStations.search(destinationStationKey);

        if (!originNode.containsDestination(destinationNode)) {
            originNode.addDestination(destinationNode, 1);
        }

        if (!destinationNode.containsDestination(originNode)) {
            destinationNode.addDestination(originNode, 1);
        }
    }

    /**
     * Returns the graph of stations
     * 
     * @return The graph of stations.
     */
    public StationGraph getGraphStations() {
        return graphStations;
    }

   /**
    * The function sets the graphStations variable to the graph passed in
    * 
    * @param graphStations The graph of stations.
    */
    public void setGraphStations(StationGraph graphStations) {
        this.graphStations = graphStations;
    }
}
