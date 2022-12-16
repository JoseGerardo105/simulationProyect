package stibride.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A graph of stations
 * 
 * @author
 */
public class StationGraph {

    private Set<StationNode> nodes;

    public StationGraph() {
        nodes = new HashSet<>();
    }

    /**
     * Add a station node to the graph
     * 
     * @param node The node to add to the graph.
     */
    public void addNode(StationNode node) {
        nodes.add(node);
    }

    /**
     * Search for a station node in the graph by its key
     * 
     * @param key The key of the station to search for.
     * @return The node that contains the station with the given key.
     */
    public StationNode search(int key) {
        for (Iterator<StationNode> it = nodes.iterator(); it.hasNext();) {
            StationNode node = it.next();
            if ((key == node.getStation().getKey())) {
                return node;
            }
        }
        return null;
    }

    /**
     * Search for a station node in the graph by its name
     * 
     * @param stationName The name of the station to search for.
     * @return The StationNode that matches the station name.
     */
    public StationNode search(String stationName) {
        for (Iterator<StationNode> it = nodes.iterator(); it.hasNext();) {
            StationNode node = it.next();
            if ((stationName.equals(node.getStation().getName()))) {
                return node;
            }
        }
        return null;
    }

    /**
     * Clear the shortest path and distance information for all nodes.
     */
    public void clearResearch() {
        for (Iterator<StationNode> it = nodes.iterator(); it.hasNext();) {
            StationNode node = it.next();
            node.clearShortestPath();
            node.setDistance(Integer.MAX_VALUE);
        }
    }

    /**
     * Returns the set of nodes in the graph
     * 
     * @return The set of nodes.
     */
    public Set<StationNode> getNodes() {
        return nodes;
    }
}
