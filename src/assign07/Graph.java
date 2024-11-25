package assign07;

import java.util.*;
/**
 * Graph Class that creates a graph using its subclasses Vertex and Edge
 * Graph Class implements the BFS, BFSWeighted, topoSort
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/24/2024
 */
public class Graph<Type> {
    private final List<Type> src;
    private final List<Type> dst;
    private final List<Double> weight;
    private final HashMap<Type, Vertex> adjMap = new HashMap<Type, Vertex>();

    private final Comparator<Vertex> vertexComparator = (Vertex obj1, Vertex obj2)->
            (Double.compare(obj2.distance, obj1.distance));

    /**
     * Constructor that creates a graph
     *
     * @param src List of starting edge generic data values
     * @param dst List of ending edge generic data values
     * @throws IllegalArgumentException if the size of the sources doesn't match the destinations
     */
    public Graph (List<Type> src, List<Type> dst) throws IllegalArgumentException {
        if (src.size() != dst.size()) {
            throw new IllegalArgumentException("source and destinations are not the same size");
        }

        this.src = src;
        this.dst = dst;
        this.weight = new ArrayList<>(src.size());
        for(int i = 0; i < src.size(); i++){
           this.weight.add(1.0);
        }
        createMap();
    }
    /**
     * Constructor that creates a weighted graph
     *
     * @param src List of starting edge generic data values
     * @param dst List of ending edge generic data values
     * @param weight List of Double values that hold the weight of each edge
     * @throws IllegalArgumentException if the size of the sources doesn't match the destinations
     */
    public Graph (List<Type> src, List<Type> dst, List<Double> weight) throws IllegalArgumentException {
        if (src.size() != dst.size() || weight.size() != src.size()) {
            throw new IllegalArgumentException("source, destinations, and weights are not the same size");
        }
        this.src = src;
        this.dst = dst;
        this.weight = weight;
        for(int i = 0; i < this.weight.size(); i++){
           if(this.weight.get(i) < 0)
               throw new IllegalArgumentException("Weight cannot be negative");

        }
        createMap();
    }

    /**
     * Method is called from the constructor to add values to a hashmap
     * The hashmap help to create the graph and is the adj map
     */
    private void createMap() {
        for(int i = 0; i < src.size(); i++) {
            if(!adjMap.containsKey(src.get(i))){
                adjMap.put(src.get(i), new Vertex(src.get(i)));
            }
            if(!adjMap.containsKey(dst.get(i))){
                adjMap.put(dst.get(i), new Vertex(dst.get(i)));
            }
            adjMap.get(src.get(i)).addEdge(adjMap.get(src.get(i)), adjMap.get(dst.get(i)), weight.get(i));
        }
    }

    /**
     * Method finds the shortest path between two vertices
     * Method uses BFS
     *
     * @param src Generic data value to start the search from
     * @param dst dstData Generic data value to find
     * @return List that hold the order of Vertices data in order of the path
     * @throws IllegalArgumentException if no path exists
     */
    public List<Type> BFS(Type src, Type dst) throws IllegalArgumentException {
        if (!adjMap.containsKey(src) || !adjMap.containsKey(dst)) {
            throw new IllegalArgumentException("elements are not in the graph");
        }

        Queue<Vertex> q = new LinkedList<>();
        Vertex s = adjMap.get(src);
        s.visited = true;
        Vertex v;
        q.offer(s);
        while (!q.isEmpty()) {
            v = q.poll();
            if (v.data.equals(dst)) {
                LinkedList<Type> path = new LinkedList<>();
                while (v != null) {
                    path.addFirst(v.data);
                    v = v.previous;
                }
                return path;
            }
            for (Edge edge : v.edges) {
                if (!edge.dst.visited) {
                    edge.dst.visited = true;
                    edge.dst.distance = v.distance + 1;
                    edge.dst.previous = v;
                    q.offer(edge.dst);
                }
            }
        }
        throw new IllegalArgumentException("no path found");
    }

    /**
     * Method finds the shortest path between two vertices using weight
     * Method uses Dijkstra's Algorithm
     *
     * @param src Generic data value to start the search from
     * @param dst dstData Generic data value to find
     * @return List that hold the order of Vertices data in order of the path
     * @throws IllegalArgumentException if no path exists
     */
    public List<Type> BFSWeighted(Type src, Type dst) throws IllegalArgumentException {
        if (!adjMap.containsKey(src) || !adjMap.containsKey(dst)) {
            throw new IllegalArgumentException("elements are not in the graph");
        }

        ArrayList<Vertex> unvisited = new ArrayList<>();

        for(Vertex vertex : adjMap.values()){
            vertex.distance = Double.MAX_VALUE;
            unvisited.add(vertex);
        }

        adjMap.get(src).distance = 0.0;

        while(!unvisited.isEmpty()){
            unvisited.sort(vertexComparator);
            Vertex v = unvisited.get(unvisited.size() - 1);
            if(v.data.equals(dst) && v.distance != Double.MAX_VALUE) {
                LinkedList<Type> path = new LinkedList<>();
                while (v != null) {
                    path.addFirst(v.data);
                    v = v.previous;
                }
                return path;
            }
            for(Edge edge : v.edges){
                if(v.distance + edge.weight < edge.dst.distance){
                    edge.dst.distance = v.distance + edge.weight;
                    edge.dst.previous = v;
                }
            }
            unvisited.remove(unvisited.size() - 1);
        }
        throw new IllegalArgumentException("no path found");
    }

    /**
     * Method sorts the Vertices by topological order
     *
     * @return List that hold the order of Vertices data in topological order
     * @throws IllegalArgumentException if a cycle is found
     */
    public List<Type> topoSort() throws IllegalArgumentException {
        LinkedList<Type> path = new LinkedList<>();
        Queue<Vertex> queue = new LinkedList<>();

        for(Vertex vertex : adjMap.values()){
            if(vertex.inDegree == 0)
                queue.offer(vertex);
        }
        while(!queue.isEmpty()){
            Vertex v = queue.poll();
            path.addLast(v.data);

            for(Edge edge : v.edges) {
                edge.dst.inDegree--;
                if(edge.dst.inDegree == 0) {
                    queue.offer(edge.dst);
                }
            }
        }
        if (path.size() != adjMap.size()) {
            throw new IllegalArgumentException("path contains a cycle");
        } else {
            return path;
        }
    }

    /**
     * Vertex class that holds its edges, previous, in degree, distance, and visited
     */
    public class Vertex {

        public Type data;
        public ArrayList<Edge> edges = new ArrayList<>();
        public Vertex previous = null;
        public boolean visited = false;
        public Double distance = 0.0;
        public int inDegree = 0;

        /**
         * Constructs a vertex object
         *
         * @param data the data of the object
         */
        public Vertex(Type data) {
            this.data = data;
        }

        /**
         * This method add an edge to the vertex edge list
         *
         * @param src the source of the edge
         * @param dst destination of the edge
         * @param weight the weight the edge has
         */
        public void addEdge(Vertex src, Vertex dst, Double weight){
            edges.add(new Edge(src, dst, weight));
            dst.inDegree++;
        }
    }

    /**
     * Edge class that represents the connections between vertices
     */
    public class Edge{
        public Vertex src;
        public Vertex dst;
        public Double weight;

        /**
         * Constructs an edge object
         *
         * @param src source
         * @param dst destination
         * @param weight weight of the edge
         */
        public Edge(Vertex src, Vertex dst, Double weight){
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }
    }
}