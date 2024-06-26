package Ej2;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import Ej2.GraphService;

public class GetNRegularTest {
    private GraphService<Character, EmptyEdgeProp> graph;

    @Test(expected = RuntimeException.class)
    public void testGetNRegularCaseA() {
        graph = new GraphBuilder<Character, EmptyEdgeProp>()
                .withAcceptSelfLoop(GraphService.SelfLoop.YES)
                .withAcceptWeight(GraphService.Weight.NO)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withStorage(GraphService.Storage.SPARSE)
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .build();
        graph.getNRegular();
    }

    @Test
    public void testGetNRegularCaseB() {
        graph = new GraphBuilder<Character, EmptyEdgeProp>()
                .withAcceptSelfLoop(GraphService.SelfLoop.NO)
                .withAcceptWeight(GraphService.Weight.NO)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withStorage(GraphService.Storage.SPARSE)
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .build();
        graph.addEdge('A', 'E', new EmptyEdgeProp());
        graph.addEdge('A', 'X', new EmptyEdgeProp());
        graph.addEdge('A', 'U', new EmptyEdgeProp());
        graph.addEdge('E', 'X', new EmptyEdgeProp());
        graph.addEdge('X', 'Q', new EmptyEdgeProp());
        graph.addEdge('Q', 'P', new EmptyEdgeProp());
        graph.addEdge('Q', 'U', new EmptyEdgeProp());
        graph.addEdge('P', 'U', new EmptyEdgeProp());
        graph.addEdge('P', 'E', new EmptyEdgeProp());
        assertEquals(3, graph.getNRegular());
    }

    @Test
    public void testGetNRegularCaseC() {
        graph = new GraphBuilder<Character, EmptyEdgeProp>()
                .withAcceptSelfLoop(GraphService.SelfLoop.NO)
                .withAcceptWeight(GraphService.Weight.NO)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withStorage(GraphService.Storage.SPARSE)
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .build();
        /*
        * lista de edges: a-e, v-c, x-y, u-w
        * */
        graph.addEdge('A', 'E', new EmptyEdgeProp());
        graph.addEdge('V', 'C', new EmptyEdgeProp());
        graph.addEdge('X', 'Y', new EmptyEdgeProp());
        graph.addEdge('U', 'W', new EmptyEdgeProp());
        assertEquals(1, graph.getNRegular());
    }

    @Test
    public void testGetNRegularCaseD() {
        graph = new GraphBuilder<Character, EmptyEdgeProp>()
                .withAcceptSelfLoop(GraphService.SelfLoop.NO)
                .withAcceptWeight(GraphService.Weight.NO)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withStorage(GraphService.Storage.SPARSE)
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .build();
        graph.addEdge('A', 'E', new EmptyEdgeProp());
        graph.addEdge('X', 'Y', new EmptyEdgeProp());
        graph.addEdge('V', 'C', new EmptyEdgeProp());
        graph.addEdge('U', 'W', new EmptyEdgeProp());
        graph.addVertex('Q');
        assertEquals(-1, graph.getNRegular());
    }

    @Test
    public void testGetNRegularCaseE() {
        graph = new GraphBuilder<Character, EmptyEdgeProp>()
                .withAcceptSelfLoop(GraphService.SelfLoop.NO)
                .withAcceptWeight(GraphService.Weight.NO)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withStorage(GraphService.Storage.SPARSE)
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .build();
        /*
        * lista de edges: a-e, a-x, e-x, x-q, e-p, q-p, q-u, p-u
        * */
        graph.addEdge('A', 'E', new EmptyEdgeProp());
        graph.addEdge('A', 'X', new EmptyEdgeProp());
        graph.addEdge('E', 'X', new EmptyEdgeProp());
        graph.addEdge('X', 'Q', new EmptyEdgeProp());
        graph.addEdge('E', 'P', new EmptyEdgeProp());
        graph.addEdge('Q', 'P', new EmptyEdgeProp());
        graph.addEdge('Q', 'U', new EmptyEdgeProp());
        graph.addEdge('P', 'U', new EmptyEdgeProp());
        assertEquals(-1, graph.getNRegular());
    }
}
