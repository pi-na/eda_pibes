import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Ej1_RemovesTests {

  @Test
  void testNodeRemovalWorks() {
    Graph graph = TestGraphFactory.undirectedUnweightedGraph();

    graph.removeNode("C");
    assertNull(graph.nodes.get("C"));
    for (Graph.Node node : graph.nodes.values()) {
      for (Graph.Node edgeNode : node.edges) {
        assertNotEquals(edgeNode.label, "C");
      }
    }
  }

  @Test
  void testEdgeRemovalWorks() {
    Graph graph = TestGraphFactory.undirectedUnweightedGraph();

    graph.removeEdge("C", "D");
    Graph.Node cNode = graph.nodes.get("C");
    Graph.Node dNode = graph.nodes.get("D");
    for (Graph.Node edgeNode : cNode.edges) {
     assertNotEquals(edgeNode.label, "D");
    }
    for (Graph.Node edgeNode : dNode.edges) {
      assertNotEquals(edgeNode.label, "C");
    }
  }
}
