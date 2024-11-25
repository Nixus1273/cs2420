package assign07;

import java.util.List;
/**
 * GraphUtility holds the driver methods for the following methods: BFS, BFSWeighted, topoSort
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/24/2024
 */
public class GraphUtility {
    /**
     * Driver method for the BFS search method
     *
     * @param sources List of starting edge generic data values
     * @param destinations List of ending edge generic data values
     * @param srcData Generic data value to start the search from
     * @param dstData Generic data value to find
     * @return List that hold the order of Vertices data in order of the path via BFS
     */
    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations,
                                                 Type srcData, Type dstData) {
        Graph<Type> graph = new Graph<>(sources, destinations);
        return graph.BFS(srcData, dstData);
    }

    /**
     * Driver method for the BFSWeighted search method
     *
     * @param sources List of starting edge generic data values
     * @param destinations List of ending edge generic data values
     * @param srcData Generic data value to start the search from
     * @param dstData Generic data value to find
     * @param weights List of Double values that hold the weight of each edge
     * @return List that hold the order of Vertices data in order of the path vis shortestWeightedPath
     */
    public static <Type> List<Type> shortestWeightedPath(List<Type> sources, List<Type> destinations,
                                                         List<Double> weights, Type srcData, Type dstData) {
        Graph<Type> graph = new Graph<>(sources, destinations, weights);
        return graph.BFSWeighted(srcData, dstData);
    }

    /**
     * Driver method for the topoSort sort method
     *
     * @param sources List of starting edge generic data values
     * @param destinations List of ending edge generic data values
     * @return List that hold the order of Vertices data in topological order
     */
    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
        Graph<Type> graph = new Graph<>(sources, destinations);
        return graph.topoSort();
    }
}