package stibride.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * For each node in the graph, calculate the minimum distance to the source node
 * 
 * @see https://www.baeldung.com/java-dijkstra
 * @author
 */
class Dijkstra {

    /**
     * For each node in the graph, calculate the minimum distance to the source node
     * 
     * @param graph  The graph to calculate the shortest path for.
     * @param source The node from which to calculate the shortest path.
     * @return The graph with the shortest paths from the source node to all other
     *         nodes.
     */
    public static StationGraph calculateShortestPathFromSource(StationGraph graph, StationNode source) {
        source.setDistance(0);

        Set<StationNode> settledNodes = new HashSet<>();
        Set<StationNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            StationNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<StationNode, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                StationNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    /**
     * Find the node with the lowest distance in the set of unsettled nodes
     * 
     * @param unsettledNodes A set of nodes that have not yet been settled.
     * @return The node with the lowest distance.
     */
    private static StationNode getLowestDistanceNode(Set<StationNode> unsettledNodes) {
        StationNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (StationNode node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    /**
     * If the distance from the source node to the current node is less than the
     * current distance of
     * the current node, then update the current node's distance and add the source
     * node to the
     * shortest path.
     * 
     * @param evaluationNode The node that we are evaluating.
     * @param edgeWeigh      The weight of the edge between the source node and the
     *                       evaluation node.
     * @param sourceNode     The node from which the shortest path is being
     *                       calculated.
     */
    private static void calculateMinimumDistance(StationNode evaluationNode, Integer edgeWeigh, StationNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<StationNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
