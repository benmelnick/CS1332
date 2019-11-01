import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GraphAlgorithmsTest {

    int MAX = Integer.MAX_VALUE;

    Vertex<Character> A = v('A');
    Vertex<Character> B = v('B');
    Vertex<Character> C = v('C');

    //One vertex, no connections
    Graph<Character> oneVertex = vs(A).es();

    //One vertex, connected by a self-loop, undirected
    Graph<Character> oneVertexSelfLoop = vs(A).es(e(A,A,1),e(A,A,1));

    //One vertex, connected by a self-loop, directed
    Graph<Character> oneVertexSelfLoopDirected = vs(A).es(e(A,A,1));

    //Two vertices, unconnected
    Graph<Character> twoVertex = vs(A,B).es();

    //Two vertices, unconnected, each with a directed self-loop
    Graph<Character> twoVertexSelfLoopDirected = vs(A,B).es(e(A,A,1),e(B,B,2));

    //Two vertices, unconnected, each with an undirected self-loop
    Graph<Character> twoVertexSelfLoop = vs(A,B).es(e(A,A,1),e(A,A,1),e(B,B,2),e(B,B,2));

    //Two vertices, connected by one directed edge
    Graph<Character> twoVertexConnectedDirected = vs(A,B).es(e(A,B,1));

    //Two vertices, connected by one undirected edge
    Graph<Character> twoVertexConnected = vs(A,B).es(e(A,B,1),e(B,A,1));

    //Two vertices, connected by two undirected parallel edges, each with different weights
    Graph<Character> twoVertexConnectedParallel = vs(A,B).es(e(A,B,1),e(B,A,1),e(A,B,2),e(B,A,2));

    //Two vertices, connected by two directed edges, both of which are from A to B, different weights
    Graph<Character> twoVertexConnectedParallelDirected = vs(A,B).es(e(A,B,1),e(A,B,2));

    //Two vertices, connected by one undirected edge, and each vertex has one undirected self-loop; the self-loops have heavy weights
    Graph<Character> twoVertexConnectedSelfLoopHeavy = vs(A,B).es(e(A,B,1),e(B,A,1),e(A,A,2),e(A,A,2),e(B,B,4),e(B,B,4));

    //Two vertices, connected by one undirected edge, and each vertex has one undirected self-loop; the self-loops have light weights
    Graph<Character> twoVertexConnectedSelfLoopLight = vs(A,B).es(e(A,B,8),e(B,A,8),e(A,A,2),e(A,A,2),e(B,B,4),e(B,B,4));

    //Two vertices, connected by one directed edge, and each vertex has one directed self-loop; the self-loops have heavy weights
    Graph<Character> twoVertexConnectedSelfLoopHeavyDirected = vs(A,B).es(e(A,B,1),e(A,A,2),e(B,B,4));

    //Two vertices, connected by one directed edge, and each vertex has one directed self-loop; the self-loops have light weights
    Graph<Character> twoVertexConnectedSelfLoopLightDirected = vs(A,B).es(e(A,B,8),e(A,A,2),e(B,B,4));

    //Three vertices, completely disconnected
    Graph<Character> threeVertex = vs(A,B,C).es();

    //Three vertices, A and B are connected, undirected
    Graph<Character> threeVertexOneEdge = vs(A,B,C).es(e(A,B,1),e(B,A,1));

    //Three vertices, A and B are connected by a directed edge
    Graph<Character> threeVertexOneEdgeDirected = vs(A,B,C).es(e(A,B,1));

    //Three vertices, A-B and B-C, undirected
    Graph<Character> threeVertexTwoEdge = vs(A,B,C).es(e(A,B,1),e(B,A,1),e(B,C,2),e(C,B,2));

    //Three vertices, A->B and B->C, directed
    Graph<Character> threeVertexTwoEdgeDirectedLinear = vs(A,B,C).es(e(A,B,1),e(B,C,2));

    //Three vertices, A->B and B<-C, directed
    Graph<Character> threeVertexTwoEdgeDirectedSink = vs(A,B,C).es(e(A,B,1),e(C,B,2));

    //Three vertices, A<-B and B->C, directed
    Graph<Character> threeVertexTwoEdgeDirectedSource = vs(A,B,C).es(e(B,A,1),e(B,C,2));

    //Three vertices, A-B, B-C, and A-C, undirected
    Graph<Character> threeVertexThreeEdge = vs(A,B,C).es(e(A,B,1),e(B,A,1),e(B,C,2),e(C,B,2),e(A,C,4),e(C,A,4));

    //Three vertices, A->B, B->C, and A<-C, directed
    Graph<Character> threeVertexThreeEdgeDirectedCycle = vs(A,B,C).es(e(A,B,1),e(B,C,2),e(C,A,4));

    //Three vertices, A->B, B->C, and A->C, directed
    Graph<Character> threeVertexThreeEdgeDirectedUnbalanced = vs(A,B,C).es(e(A,B,1),e(B,C,2),e(A,C,4));

    //Three vertices. A<->B, A->C, B->C. It is optimal to do B->A->C rather than B->C. A->C is still optimal.
    Graph<Character> threeVertexFourEdge = vs(A,B,C).es(e(A,B,1),e(B,A,1),e(A,C,2),e(B,C,4));

    //Three vertices, all connected to each other but with different weights in different directions.
    //The following are optimal: A->B->C, C->A, B->C->A, A->B, B->C, C->A->B
    Graph<Character> threeVertexSixEdge = vs(A,B,C).es(e(A,B,2),e(B,A,16),e(B,C,8),e(C,B,4),e(A,C,32),e(C,A,1));

    @Test
    public void bfs(){
        try{
            testBFS(null,A);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        try{
            testBFS(oneVertex,null);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        try{
            testBFS(oneVertex,B);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        testBFS(oneVertex,A,A);
        testBFS(oneVertexSelfLoop,A,A);
        testBFS(oneVertexSelfLoopDirected,A,A);
        testBFS(twoVertex,A,A);
        testBFS(twoVertex,B,B);
        testBFS(twoVertexSelfLoopDirected,A,A);
        testBFS(twoVertexSelfLoopDirected,B,B);
        testBFS(twoVertexSelfLoop,A,A);
        testBFS(twoVertexSelfLoop,B,B);
        testBFS(twoVertexConnectedDirected,A,A,B);
        testBFS(twoVertexConnectedDirected,B,B);
        testBFS(twoVertexConnected,A,A,B);
        testBFS(twoVertexConnected,B,B,A);
        testBFS(twoVertexConnectedParallel,A,A,B);
        testBFS(twoVertexConnectedParallel,B,B,A);
        testBFS(twoVertexConnectedParallelDirected,A,A,B);
        testBFS(twoVertexConnectedParallelDirected,B,B);
        testBFS(twoVertexConnectedSelfLoopHeavy,A,A,B);
        testBFS(twoVertexConnectedSelfLoopHeavy,B,B,A);
        testBFS(twoVertexConnectedSelfLoopHeavyDirected,A,A,B);
        testBFS(twoVertexConnectedSelfLoopHeavyDirected,B,B);
        testBFS(threeVertex,A,A);
        testBFS(threeVertex,B,B);
        testBFS(threeVertex,C,C);
        testBFS(threeVertexOneEdge,A,A,B);
        testBFS(threeVertexOneEdge,B,B,A);
        testBFS(threeVertexOneEdge,C,C);
        testBFS(threeVertexOneEdgeDirected,A,A,B);
        testBFS(threeVertexOneEdgeDirected,B,B);
        testBFS(threeVertexOneEdgeDirected,C,C);
        testBFS(threeVertexTwoEdge,A,A,B,C);
        testBFS(threeVertexTwoEdge,B,B,A,C);
        testBFS(threeVertexTwoEdge,C,C,B,A);
        testBFS(threeVertexTwoEdgeDirectedLinear,A,A,B,C);
        testBFS(threeVertexTwoEdgeDirectedLinear,B,B,C);
        testBFS(threeVertexTwoEdgeDirectedLinear,C,C);
        testBFS(threeVertexTwoEdgeDirectedSink,A,A,B);
        testBFS(threeVertexTwoEdgeDirectedSink,B,B);
        testBFS(threeVertexTwoEdgeDirectedSink,C,C,B);
        testBFS(threeVertexTwoEdgeDirectedSource,A,A);
        testBFS(threeVertexTwoEdgeDirectedSource,B,B,A,C);
        testBFS(threeVertexTwoEdgeDirectedSource,C,C);
        testBFS(threeVertexThreeEdge,A,A,B,C);
        testBFS(threeVertexThreeEdge,B,B,A,C);
        testBFS(threeVertexThreeEdge,C,C,B,A);
        testBFS(threeVertexThreeEdgeDirectedCycle,A,A,B,C);
        testBFS(threeVertexThreeEdgeDirectedCycle,B,B,C,A);
        testBFS(threeVertexThreeEdgeDirectedCycle,C,C,A,B);
        testBFS(threeVertexThreeEdgeDirectedUnbalanced,A,A,B,C);
        testBFS(threeVertexThreeEdgeDirectedUnbalanced,B,B,C);
        testBFS(threeVertexThreeEdgeDirectedUnbalanced,C,C);
        testBFS(threeVertexFourEdge,A,A,C,B);
        testBFS(threeVertexFourEdge,B,B,A,C);
        testBFS(threeVertexFourEdge,C,C);
    }

    @Test
    public void dfs(){
        try{
            testDFS(null,A);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        try{
            testDFS(oneVertex,null);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        try{
            testDFS(oneVertex,B);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        testDFS(oneVertex,A,A);
        testDFS(oneVertexSelfLoop,A,A);
        testDFS(oneVertexSelfLoopDirected,A,A);
        testDFS(twoVertex,A,A);
        testDFS(twoVertex,B,B);
        testDFS(twoVertexSelfLoopDirected,A,A);
        testDFS(twoVertexSelfLoopDirected,B,B);
        testDFS(twoVertexSelfLoop,A,A);
        testDFS(twoVertexSelfLoop,B,B);
        testDFS(twoVertexConnectedDirected,A,A,B);
        testDFS(twoVertexConnectedDirected,B,B);
        testDFS(twoVertexConnected,A,A,B);
        testDFS(twoVertexConnected,B,B,A);
        testDFS(twoVertexConnectedParallel,A,A,B);
        testDFS(twoVertexConnectedParallel,B,B,A);
        testDFS(twoVertexConnectedParallelDirected,A,A,B);
        testDFS(twoVertexConnectedParallelDirected,B,B);
        testDFS(twoVertexConnectedSelfLoopHeavy,A,A,B);
        testDFS(twoVertexConnectedSelfLoopHeavy,B,B,A);
        testDFS(twoVertexConnectedSelfLoopHeavyDirected,A,A,B);
        testDFS(twoVertexConnectedSelfLoopHeavyDirected,B,B);
        testDFS(threeVertex,A,A);
        testDFS(threeVertex,B,B);
        testDFS(threeVertex,C,C);
        testDFS(threeVertexOneEdge,A,A,B);
        testDFS(threeVertexOneEdge,B,B,A);
        testDFS(threeVertexOneEdge,C,C);
        testDFS(threeVertexOneEdgeDirected,A,A,B);
        testDFS(threeVertexOneEdgeDirected,B,B);
        testDFS(threeVertexOneEdgeDirected,C,C);
        testDFS(threeVertexTwoEdge,A,A,B,C);
        testDFS(threeVertexTwoEdge,B,B,A,C);
        testDFS(threeVertexTwoEdge,C,C,B,A);
        testDFS(threeVertexTwoEdgeDirectedLinear,A,A,B,C);
        testDFS(threeVertexTwoEdgeDirectedLinear,B,B,C);
        testDFS(threeVertexTwoEdgeDirectedLinear,C,C);
        testDFS(threeVertexTwoEdgeDirectedSink,A,A,B);
        testDFS(threeVertexTwoEdgeDirectedSink,B,B);
        testDFS(threeVertexTwoEdgeDirectedSink,C,C,B);
        testDFS(threeVertexTwoEdgeDirectedSource,A,A);
        testDFS(threeVertexTwoEdgeDirectedSource,B,B,A,C);
        testDFS(threeVertexTwoEdgeDirectedSource,C,C);
        testDFS(threeVertexThreeEdge,A,A,B,C);
        testDFS(threeVertexThreeEdge,B,B,A,C);
        testDFS(threeVertexThreeEdge,C,C,B,A);
        testDFS(threeVertexThreeEdgeDirectedCycle,A,A,B,C);
        testDFS(threeVertexThreeEdgeDirectedCycle,B,B,C,A);
        testDFS(threeVertexThreeEdgeDirectedCycle,C,C,A,B);
        testDFS(threeVertexThreeEdgeDirectedUnbalanced,A,A,B,C);
        testDFS(threeVertexThreeEdgeDirectedUnbalanced,B,B,C);
        testDFS(threeVertexThreeEdgeDirectedUnbalanced,C,C);
        testDFS(threeVertexFourEdge,A,A,C,B);
        testDFS(threeVertexFourEdge,B,B,A,C);
        testDFS(threeVertexFourEdge,C,C);
    }

    @Test
    public void dijkstra(){
        try{
            testD(null,A);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        try{
            testD(oneVertex,null);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        try{
            testD(oneVertex,B);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        testD(oneVertex,A,0);
        testD(oneVertexSelfLoop,A,0);
        testD(oneVertexSelfLoopDirected,A,0);
        testD(twoVertex,A,0,MAX);
        testD(twoVertex,B,MAX,0);
        testD(twoVertexSelfLoopDirected,A,0,MAX);
        testD(twoVertexSelfLoopDirected,B,MAX,0);
        testD(twoVertexSelfLoop,A,0,MAX);
        testD(twoVertexSelfLoop,B,MAX,0);
        testD(twoVertexConnectedDirected,A,0,1);
        testD(twoVertexConnectedDirected,B,MAX,0);
        testD(twoVertexConnected,A,0,1);
        testD(twoVertexConnected,B,1,0);
        testD(twoVertexConnectedParallel,A,0,1);
        testD(twoVertexConnectedParallel,B,1,0);
        testD(twoVertexConnectedParallelDirected,A,0,1);
        testD(twoVertexConnectedParallelDirected,B,MAX,0);
        testD(twoVertexConnectedSelfLoopHeavy,A,0,1);
        testD(twoVertexConnectedSelfLoopHeavy,B,1,0);
        testD(twoVertexConnectedSelfLoopLight,A,0,8);
        testD(twoVertexConnectedSelfLoopLight,B,8,0);
        testD(twoVertexConnectedSelfLoopHeavyDirected,A,0,1);
        testD(twoVertexConnectedSelfLoopHeavyDirected,B,MAX,0);
        testD(twoVertexConnectedSelfLoopLightDirected,A,0,8);
        testD(twoVertexConnectedSelfLoopLightDirected,B,MAX,0);
        testD(threeVertex,A,0,MAX,MAX);
        testD(threeVertex,B,MAX,0,MAX);
        testD(threeVertex,C,MAX,MAX,0);
        testD(threeVertexOneEdge,A,0,1,MAX);
        testD(threeVertexOneEdge,B,1,0,MAX);
        testD(threeVertexOneEdge,C,MAX,MAX,0);
        testD(threeVertexOneEdgeDirected,A,0,1,MAX);
        testD(threeVertexOneEdgeDirected,B,MAX,0,MAX);
        testD(threeVertexOneEdgeDirected,C,MAX,MAX,0);
        testD(threeVertexTwoEdge,A,0,1,3);
        testD(threeVertexTwoEdge,B,1,0,2);
        testD(threeVertexTwoEdge,C,3,2,0);
        testD(threeVertexTwoEdgeDirectedLinear,A,0,1,3);
        testD(threeVertexTwoEdgeDirectedLinear,B,MAX,0,2);
        testD(threeVertexTwoEdgeDirectedLinear,C,MAX,MAX,0);
        testD(threeVertexTwoEdgeDirectedSink,A,0,1,MAX);
        testD(threeVertexTwoEdgeDirectedSink,B,MAX,0,MAX);
        testD(threeVertexTwoEdgeDirectedSink,C,MAX,2,0);
        testD(threeVertexTwoEdgeDirectedSource,A,0,MAX,MAX);
        testD(threeVertexTwoEdgeDirectedSource,B,1,0,2);
        testD(threeVertexTwoEdgeDirectedSource,C,MAX,MAX,0);
        testD(threeVertexThreeEdge,A,0,1,3);
        testD(threeVertexThreeEdge,B,1,0,2);
        testD(threeVertexThreeEdge,C,3,2,0);
        testD(threeVertexThreeEdgeDirectedCycle,A,0,1,3);
        testD(threeVertexThreeEdgeDirectedCycle,B,6,0,2);
        testD(threeVertexThreeEdgeDirectedCycle,C,4,5,0);
        testD(threeVertexThreeEdgeDirectedUnbalanced,A,0,1,3);
        testD(threeVertexThreeEdgeDirectedUnbalanced,B,MAX,0,2);
        testD(threeVertexThreeEdgeDirectedUnbalanced,C,MAX,MAX,0);
        testD(threeVertexFourEdge,A,0,1,2);
        testD(threeVertexFourEdge,B,1,0,3);
        testD(threeVertexFourEdge,C,MAX,MAX,0);
        testD(threeVertexSixEdge,A,0,2,10);
        testD(threeVertexSixEdge,B,9,0,8);
        testD(threeVertexSixEdge,C,1,3,0);
    }

    @Test
    public void kruskal(){
        try{
            testK(null);
            fail("No exception thrown");
        } catch (IllegalArgumentException e){}
        testK(oneVertex);
        testK(oneVertexSelfLoop);
        testKN(twoVertex);
        testKN(twoVertexSelfLoop);
        testK(twoVertexConnected,e(A,B,1),e(B,A,1));
        testK(twoVertexConnectedParallel,e(A,B,1),e(B,A,1));
        testK(twoVertexConnectedSelfLoopHeavy,e(A,B,1),e(B,A,1));
        testK(twoVertexConnectedSelfLoopLight,e(A,B,8),e(B,A,8));
        testKN(threeVertex);
        testKN(threeVertexOneEdge);
        testK(threeVertexTwoEdge,e(A,B,1),e(B,A,1),e(B,C,2),e(C,B,2));
        testK(threeVertexThreeEdge,e(A,B,1),e(B,A,1),e(B,C,2),e(C,B,2));
    }

    public void testBFS(Graph<Character> g, Vertex<Character> s, Vertex<Character>... trav){
        List<Vertex<Character>> l = GraphAlgorithms.breadthFirstSearch(s,g);
        assertEquals(Arrays.asList(trav), l);
    }

    public void testDFS(Graph<Character> g, Vertex<Character> s, Vertex<Character>... trav){
        List<Vertex<Character>> l = GraphAlgorithms.depthFirstSearch(s,g);
        assertEquals(Arrays.asList(trav), l);
    }

    public void testD(Graph<Character> g, Vertex<Character> s, int... ns){
        Map<Vertex<Character>,Integer> m = GraphAlgorithms.dijkstras(s,g);
        Vertex<Character>[] vxs = new Vertex[3];
        vxs[0]=A;vxs[1]=B;vxs[2]=C;
        Map<Vertex<Character>,Integer> mm = new HashMap<>();
        for (int i=0;i<ns.length;i++) {
            mm.put(vxs[i],ns[i]);
        }
        assertEquals(mm,m);
    }

    public void testK(Graph<Character> g, Edge<Character>... eds){
        Set<Edge<Character>> eee = GraphAlgorithms.kruskals(g);
        assertEquals(new HashSet<>(Arrays.asList(eds)),eee);
    }

    public void testKN(Graph<Character> g){
        Set<Edge<Character>> eee = GraphAlgorithms.kruskals(g);
        assertNull(eee);
    }

    public static GraphBuilder vs(Vertex<Character>... verts){
        return new GraphBuilder(verts);
    }

    public static Vertex<Character> v(Character c){
        return new Vertex<>(c);
    }

    public static Edge<Character> e(Vertex<Character> u, Vertex<Character> v, int wt){
        return new Edge<>(u,v,wt);
    }

    public static class GraphBuilder{
        public Vertex<Character>[] vertices;

        public GraphBuilder(Vertex<Character>[] vertices){
            this.vertices = vertices;
        }
        public Graph<Character> es(Edge<Character>... edges) {
            return new Graph<>(new HashSet<>(Arrays.asList(vertices)),new HashSet<>(Arrays.asList(edges)));
        }
    }
}
