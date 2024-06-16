import org.junit.jupiter.api.Test;

class GraphTest {

  @Test
  void testBfs() {
    Graph graph = TestGraphFactory.undirectedUnweightedGraph();
    graph.printBfs("A");
  }

  @Test
  void testDfs() {
    Graph graph = TestGraphFactory.undirectedUnweightedGraph();
    graph.printDfs("A");
  }

  @Test
  void testDijkstra() {
    WeightedGraph graph = TestGraphFactory.directedWeightedGraph();
    graph.printDijkstra("A");
  }

  @Test
  void testDenseBfs() {
    DenseGraph graph = TestGraphFactory.denseGraph();
    graph.printBfs("A");
  }

}