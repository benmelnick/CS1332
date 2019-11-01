import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MyGraphAlgorthimTests {
    private Graph<Integer> directedGraph;
    private Graph<Character> undirectedGraph;
    private Graph<Character> disconnectedUndirectedGraph;
    public static final int TIMEOUT = 200;

    @Before
    public void init() {
        directedGraph = createDirectedGraph();
        undirectedGraph = createUndirectedGraph();
        disconnectedUndirectedGraph = createDisconnectedUndirectedGraph();
    }

    /**
     * Creates a directed graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Integer> createDirectedGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<Vertex<Integer>>();
        for (int i = 1; i <= 10; i++) {
            vertices.add(new Vertex<Integer>(i));
        }

        Set<Edge<Integer>> edges = new LinkedHashSet<Edge<Integer>>();
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 10));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(5), 2));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(10), 1));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(3), 9));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(7), 1));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(7), 6));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(8), 7));
        edges.add(new Edge<>(new Vertex<>(5), new Vertex<>(10), 1));
        edges.add(new Edge<>(new Vertex<>(6), new Vertex<>(4), 3));
        edges.add(new Edge<>(new Vertex<>(6), new Vertex<>(7), 1));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(1), 4));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(2), 1));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(4), 2));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(8), 9));
        edges.add(new Edge<>(new Vertex<>(7), new Vertex<>(9), 2));
        edges.add(new Edge<>(new Vertex<>(8), new Vertex<>(6), 4));
        edges.add(new Edge<>(new Vertex<>(9), new Vertex<>(3), 6));

        return new Graph<Integer>(vertices, edges);
    }

    /**
     * Creates an undirected graph.
     * The graph is depicted in the pdf.
     *
     * @return the completed graph
     */
    private Graph<Character> createUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 72; i++) {
            vertices.add(new Vertex<Character>((char) i));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('F'), 3));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('C'), 3));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 9));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 9));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 5));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 5));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 3));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 6));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('G'), 4));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('F'), 2));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('G'), 2));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('E'), 7));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('H'), 7));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('G'), 1));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('H'), 1));

        return new Graph<Character>(vertices, edges);
    }

    /**
     *
     * @return disconnected undirected graph
     */
    private Graph<Character> createDisconnectedUndirectedGraph() {
        Set<Vertex<Character>> vertices = new HashSet<>();
        for (int i = 65; i <= 74; i++) {
            vertices.add(new Vertex<Character>((char) i));
        }

        Set<Edge<Character>> edges = new LinkedHashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('F'), 3));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('C'), 3));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 9));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 9));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 5));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 5));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 3));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 6));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 6));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('G'), 4));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('A'), 4));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('F'), 2));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('G'), 2));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('E'), 7));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('H'), 7));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('G'), 1));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('H'), 1));

        // Disconnected component
        edges.add(new Edge<>(new Vertex<>('I'), new Vertex<>('J'), 1));
        edges.add(new Edge<>(new Vertex<>('J'), new Vertex<>('I'), 1));

        return new Graph<Character>(vertices, edges);
    }


    @Test(timeout = TIMEOUT)
    public void testBreadthFirstSearch() {
        List<Vertex<Integer>> bfsActual = GraphAlgorithms.breadthFirstSearch(
                new Vertex<Integer>(1), directedGraph);

        List<Vertex<Integer>> bfsExpected = new LinkedList<>();
        bfsExpected.add(new Vertex<Integer>(1));
        bfsExpected.add(new Vertex<Integer>(2));
        bfsExpected.add(new Vertex<Integer>(5));
        bfsExpected.add(new Vertex<Integer>(10));
        bfsExpected.add(new Vertex<Integer>(3));
        bfsExpected.add(new Vertex<Integer>(7));
        bfsExpected.add(new Vertex<Integer>(8));
        bfsExpected.add(new Vertex<Integer>(4));
        bfsExpected.add(new Vertex<Integer>(9));
        bfsExpected.add(new Vertex<Integer>(6));


        assertEquals(bfsExpected, bfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDepthFirstSearch() {
        List<Vertex<Integer>> dfsActual = GraphAlgorithms.depthFirstSearch(
                new Vertex<Integer>(1), directedGraph);

        List<Vertex<Integer>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<Integer>(1));
        dfsExpected.add(new Vertex<Integer>(2));
        dfsExpected.add(new Vertex<Integer>(3));
        dfsExpected.add(new Vertex<Integer>(7));
        dfsExpected.add(new Vertex<Integer>(4));
        dfsExpected.add(new Vertex<Integer>(8));
        dfsExpected.add(new Vertex<Integer>(6));
        dfsExpected.add(new Vertex<Integer>(9));
        dfsExpected.add(new Vertex<Integer>(5));
        dfsExpected.add(new Vertex<Integer>(10));

        assertEquals(dfsExpected, dfsActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstrasUndirected() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<Character>('A'), undirectedGraph);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 0);
        dijkExpected.put(new Vertex<>('B'), 1);
        dijkExpected.put(new Vertex<>('C'), 1);
        dijkExpected.put(new Vertex<>('D'), 4);
        dijkExpected.put(new Vertex<>('E'), 9);
        dijkExpected.put(new Vertex<>('F'), 4);
        dijkExpected.put(new Vertex<>('G'), 4);
        dijkExpected.put(new Vertex<>('H'), 5);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstrasDisconnected() {
        Map<Vertex<Character>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<Character>('A'), disconnectedUndirectedGraph);
        Map<Vertex<Character>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>('A'), 0);
        dijkExpected.put(new Vertex<>('B'), 1);
        dijkExpected.put(new Vertex<>('C'), 1);
        dijkExpected.put(new Vertex<>('D'), 4);
        dijkExpected.put(new Vertex<>('E'), 9);
        dijkExpected.put(new Vertex<>('F'), 4);
        dijkExpected.put(new Vertex<>('G'), 4);
        dijkExpected.put(new Vertex<>('H'), 5);
        dijkExpected.put(new Vertex<>('I'), Integer.MAX_VALUE);
        dijkExpected.put(new Vertex<>('J'), Integer.MAX_VALUE);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testDijkstrasDirected() {
        Map<Vertex<Integer>, Integer> dijkActual = GraphAlgorithms.dijkstras(
                new Vertex<Integer>(1), directedGraph);
        Map<Vertex<Integer>, Integer> dijkExpected = new HashMap<>();
        dijkExpected.put(new Vertex<>(1), 0);
        dijkExpected.put(new Vertex<>(2), 9);
        dijkExpected.put(new Vertex<>(3), 16);
        dijkExpected.put(new Vertex<>(4), 10);
        dijkExpected.put(new Vertex<>(5), 2);
        dijkExpected.put(new Vertex<>(6), 13);
        dijkExpected.put(new Vertex<>(7), 8);
        dijkExpected.put(new Vertex<>(8), 9);
        dijkExpected.put(new Vertex<>(9), 10);
        dijkExpected.put(new Vertex<>(10), 1);

        assertEquals(dijkExpected, dijkActual);
    }

    @Test(timeout = TIMEOUT)
    public void testKruskalsConnected() {
        Set<Edge<Character>> mstActual = GraphAlgorithms.kruskals(
                undirectedGraph);
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 1));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('G'), 1));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('H'), 1));
        edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('F'), 2));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('G'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 3));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 3));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('F'), 3));
        edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('C'), 3));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 5));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 5));

        assertEquals(edges, mstActual);
    }

    @Test(timeout = TIMEOUT)
    public void testKruskalsDisconnected() {

        Set<Edge<Character>> mstActual = GraphAlgorithms.kruskals(
                disconnectedUndirectedGraph);
        assertEquals(null, mstActual);
    }

}