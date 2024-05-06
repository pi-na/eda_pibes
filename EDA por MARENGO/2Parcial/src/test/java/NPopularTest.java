import graph.EmptyEdgeProp;
import graph.GraphBuilder;
import graph.GraphService;
import graph.WeightedEdge;
import graph.EmptyEdgeProp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NPopularTest {
    private GraphService<String, WeightedEdge> directedMultipleLoopWeight;
    private GraphService<String, WeightedEdge> directedSimpleLoopWeight;
    private GraphService<Character, EmptyEdgeProp> directedMultipleLoopNoWeight;

    @BeforeEach
    void setup() {
        directedMultipleLoopWeight = new GraphBuilder<String, WeightedEdge>()
                .withStorage(GraphService.Storage.SPARSE)
                .withAcceptSelfLoop(GraphService.SelfLoop.YES)
                .withAcceptWeight(GraphService.Weight.YES)
                .withDirected(GraphService.EdgeMode.DIRECTED)
                .withMultiplicity(GraphService.Multiplicity.MULTIPLE)
                .build();

        directedSimpleLoopWeight = new GraphBuilder<String, WeightedEdge>()
                .withStorage(GraphService.Storage.SPARSE)
                .withAcceptSelfLoop(GraphService.SelfLoop.YES)
                .withAcceptWeight(GraphService.Weight.YES)
                .withDirected(GraphService.EdgeMode.DIRECTED)
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .build();

        directedMultipleLoopNoWeight = new GraphBuilder<Character, EmptyEdgeProp>()
                .withStorage(GraphService.Storage.SPARSE)
                .withAcceptSelfLoop(GraphService.SelfLoop.YES)
                .withAcceptWeight(GraphService.Weight.NO)
                .withDirected(GraphService.EdgeMode.DIRECTED)
                .withMultiplicity(GraphService.Multiplicity.MULTIPLE)
                .build();
    }

    @Test
    public void test1Simple() {
        directedSimpleLoopWeight.addEdge("Juan", "Pink", new WeightedEdge(2));
        directedSimpleLoopWeight.addEdge("Juan", "Eiti Eda", new WeightedEdge(2));
        directedSimpleLoopWeight.addEdge("Ale", "Eiti Eda", new WeightedEdge(4));
        directedSimpleLoopWeight.addEdge("Ale", "Call me maybe", new WeightedEdge(1));
        directedSimpleLoopWeight.addEdge("Mer", "Eiti Eda", new WeightedEdge(1));
        directedSimpleLoopWeight.addEdge("Mer", "Pink", new WeightedEdge(2));
        directedSimpleLoopWeight.addEdge("Mer", "Call me maybe", new WeightedEdge(9));

        GraphService<String, WeightedEdge> nPopularGraph = directedSimpleLoopWeight.popularSubgraph(8);

        Assertions.assertTrue(nPopularGraph.hasEdge("Ale","Call me maybe", new WeightedEdge(1)));
        Assertions.assertTrue(nPopularGraph.hasEdge("Mer","Call me maybe", new WeightedEdge(9)));
        Assertions.assertFalse(nPopularGraph.hasEdge("Ale","Call me maybe", new WeightedEdge(8)));
        Assertions.assertFalse(nPopularGraph.hasEdge("Juan","Pink", new WeightedEdge(2)));
    }

    @Test
    public void test1Multiple() {
        directedMultipleLoopWeight.addEdge("Juan", "Pink", new WeightedEdge(2));
        directedMultipleLoopWeight.addEdge("Juan", "Eiti Eda", new WeightedEdge(2));
        directedMultipleLoopWeight.addEdge("Ale", "Eiti Eda", new WeightedEdge(4));
        directedMultipleLoopWeight.addEdge("Ale", "Call me maybe", new WeightedEdge(1));
        directedMultipleLoopWeight.addEdge("Mer", "Eiti Eda", new WeightedEdge(1));
        directedMultipleLoopWeight.addEdge("Mer", "Pink", new WeightedEdge(2));
        directedMultipleLoopWeight.addEdge("Mer", "Call me maybe", new WeightedEdge(9));
        directedMultipleLoopWeight.addEdge("Mer", "Eiti Eda", new WeightedEdge(10));

        GraphService<String, WeightedEdge> nPopularGraph = directedMultipleLoopWeight.popularSubgraph(8);

        Assertions.assertTrue(nPopularGraph.hasEdge("Ale","Call me maybe", new WeightedEdge(1)));
        Assertions.assertTrue(nPopularGraph.hasEdge("Mer","Call me maybe", new WeightedEdge(9)));
        Assertions.assertFalse(nPopularGraph.hasEdge("Ale","Call me maybe", new WeightedEdge(8)));
        Assertions.assertFalse(nPopularGraph.hasEdge("Juan","Pink", new WeightedEdge(2)));
        Assertions.assertTrue(nPopularGraph.hasEdge("Mer", "Eiti Eda", new WeightedEdge(10)));
    }

    @Test
    public void test1Exception() {
        Assertions.assertThrows(RuntimeException.class, () -> directedMultipleLoopNoWeight.popularSubgraph(8));
    }
}
