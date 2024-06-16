import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ej3_ConnectedComponentsTest {

  @Test
  void testConnectedComponentsWorks() {
    Graph graph = TestGraphFactory.undirectedUnweightedGraph();

    assertEquals(1, graph.connectedComponents());
    graph.removeEdge("C", "F");
    assertEquals(1, graph.connectedComponents());
    graph.removeEdge("D", "E");
    assertEquals(2, graph.connectedComponents());
    graph.removeEdge("E", "G");
    assertEquals(3, graph.connectedComponents());
    graph.removeEdge("A", "B");
    assertEquals(3, graph.connectedComponents());
    graph.removeEdge("A", "C");
    assertEquals(4, graph.connectedComponents());
  }
}
