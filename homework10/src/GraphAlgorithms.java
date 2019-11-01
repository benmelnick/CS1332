import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                                         Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("The start "
                    + "node cannot be null.");
        }
        if (graph == null) {
            throw new IllegalArgumentException("The graph is null.");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The start "
                    + "node is not in this graph.");
        }

        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        HashSet<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> returnList = new LinkedList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> v = queue.remove();
            returnList.add(v);
            for (VertexDistance<T> w : adjList.get(v)) {
                if (!visited.contains(w.getVertex())) {
                    visited.add(w.getVertex());
                    queue.add(w.getVertex());
                }
            }
        }
        return returnList;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                                       Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("The start "
                    + "node cannot be null.");
        }
        if (graph == null) {
            throw new IllegalArgumentException("The graph is null.");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The start "
                    + "node is not in this graph.");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> returnList = new LinkedList<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        dfsHelper(start, graph, visited, returnList, adjList);
        return returnList;
    }

    /**
     * recursive helper method for performing a depth first search
     * @param v current vertex
     * @param graph the graph to search through
     * @param visited set of the vertices in the graph that have been visited
     * @param result list of vertices that will be returned
     * @param adj adjacency list of the graph
     * @param <T> generic typing of the data
     */
    private static <T> void dfsHelper(Vertex<T> v, Graph<T> graph,
                                      Set<Vertex<T>> visited,
                                      List<Vertex<T>> result,
                                      Map<Vertex<T>,
                                              List<VertexDistance<T>>> adj) {
        if (visited.size() == graph.getVertices().size()) {
            return;
        } else {
            visited.add(v);
            result.add(v);
            List<VertexDistance<T>> vertices = adj.get(v);
            for (VertexDistance<T> w : vertices) {
                if (!visited.contains(w.getVertex())) {
                    dfsHelper(w.getVertex(), graph, visited, result, adj);
                }
            }
        }
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from {@code start}, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check that not all vertices have been visited.
     * 2) Check that the PQ is not empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from {@code start} to every
     *          other node in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("The start "
                    + "node cannot be null.");
        }
        if (graph == null) {
            throw new IllegalArgumentException("The graph is null.");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The start "
                    + "node is not in this graph.");
        }

        PriorityQueue<VertexDistance<T>> pQueue = new PriorityQueue<>();
        Map<Vertex<T>, Integer> result = new HashMap<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        Set<Vertex<T>> visited = new HashSet<>();
        //creating the initial table
        for (Vertex<T> v : adjList.keySet()) {
            if (v.equals(start)) {
                result.put(v, 0);
            } else {
                result.put(v, Integer.MAX_VALUE);
            }
        }
        //going through all vertices
        pQueue.add(new VertexDistance<>(start, 0));
        while (!pQueue.isEmpty() && visited.size() <= result.size()) {
            VertexDistance<T> temp = pQueue.remove();
            if (!visited.contains(temp.getVertex())) {
                visited.add(temp.getVertex());
            }
            for (VertexDistance<T> v : adjList.get(temp.getVertex())) {
                int curDist = temp.getDistance() + v.getDistance();
                if (result.get(v.getVertex()).compareTo(curDist) > 0) {
                    result.put(v.getVertex(), curDist);
                    pQueue.add(new VertexDistance<>(v.getVertex(), curDist));
                    if (!visited.contains(v.getVertex())) {
                        visited.add(v.getVertex());
                    }
                }
            }
        }
        return result;
    }


    /**
     * Runs Kruskal's algorithm on the given graph and returns the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the {@code DisjointSet} and {@code DisjointSetNode} classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops or parallel edges into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null
     * @param <T> the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("The graph is null.");
        }
        DisjointSet<Vertex<T>> disSet = new DisjointSet<>(graph.getVertices());
        Set<Edge<T>> returnSet = new HashSet<>();
        PriorityQueue<Edge<T>> queue =  new PriorityQueue<>(graph.getEdges());

        while (returnSet.size() < 2 * (graph.getVertices().size() - 1)) {
            Edge<T> currEdge = queue.poll();
            if (currEdge == null) {
                return null;
            }
            Vertex<T> currU = currEdge.getU();
            Vertex<T> currV = currEdge.getV();
            if (!disSet.find(currU).equals(disSet.find(currV))) {
                returnSet.add(currEdge);
                returnSet.add(new Edge<>(currV, currU, currEdge.getWeight()));
                disSet.union(currU, currV);
            }
        }
        return returnSet;
    }
}
