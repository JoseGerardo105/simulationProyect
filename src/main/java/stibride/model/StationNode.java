package stibride.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import stibride.dto.StationsDto;

/**
 * A StationNode is a node in a graph that represents a station
 * 
 * @author
 */
public class StationNode {

    private StationsDto station;

    // This is a list of StationNodes that represent the shortest path from the origin to the destination.
    private List<StationNode> shortestPath;

    // The distance between the origin and destination.
    private Integer distance;

    // The map of adjacents stations to the distance between the the station and his adjacent stations.
    Map<StationNode, Integer> adjacentStations;

    public StationNode(StationsDto station) {
        this.station = station;
        shortestPath = new LinkedList<>();
        distance = Integer.MAX_VALUE;
        adjacentStations = new HashMap<>();
    }

    /**
     * Add a destination to the list of adjacent stations
     * 
     * @param destination The station that is adjacent to the current station.
     * @param distance    The distance between the two stations.
     */
    public void addDestination(StationNode destination, int distance) {
        adjacentStations.put(destination, distance);
    }

    /**
     * Given a station, return true if the station is adjacent to this station
     * 
     * @param destination The destination station.
     * @return The boolean value of whether or not the destination is contained in
     *         the adjacent
     *         stations.
     */
    public boolean containsDestination(StationNode destination) {
        for (StationNode node : adjacentStations.keySet()) {
            if (node.equals(destination)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear the shortest path.
     */
    public void clearShortestPath() {
        shortestPath.clear();
    }

    // Getters

    /**
     * Get the station
     * 
     * @return A StationsDto object.
     */
    public StationsDto getStation() {
        return station;
    }

    /**
     * Returns a map of adjacent stations and their distances
     * 
     * @return A map of StationNodes to integers.
     */
    public Map<StationNode, Integer> getAdjacentNodes() {
        return adjacentStations;
    }

    /**
     * Returns the distance between two points
     * 
     * @return The distance between the two points.
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * Find the shortest path from the origin node to the destination node
     * 
     * @return The shortest path from the origin node to the destination node.
     */
    public List<StationNode> getShortestPath() {
        return shortestPath;
    }

    // Setters

    /**
     * Set the distance between the origin and destionation of the station node
     * 
     * @param distance The distance from the origin to the destination.
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * Set the shortest path between the origin and destionation for the station
     * node
     * 
     * @param shortestPath A list of StationNodes that represent the shortest path
     *                     from the origin to
     *                     the destination.
     */
    public void setShortestPath(List<StationNode> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
