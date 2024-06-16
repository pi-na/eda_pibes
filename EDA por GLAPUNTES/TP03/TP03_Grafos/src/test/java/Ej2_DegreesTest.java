import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ej2_DegreesTest {

  @Test
  void testInDegreeWorks() {
    Graph graph = TestGraphFactory.directedUnweightedGraph();

    assertEquals(0, graph.inDegree("A"));
    assertEquals(1, graph.inDegree("B"));
    assertEquals(1, graph.inDegree("C"));
    assertEquals(2, graph.inDegree("D"));
    assertEquals(2, graph.inDegree("E"));
    assertEquals(1, graph.inDegree("F"));
    assertEquals(1, graph.inDegree("G"));
  }

  @Test
  void testOutDegreeWorks() {
    Graph graph = TestGraphFactory.directedUnweightedGraph();

    assertEquals(2, graph.outDegree("A"));
    assertEquals(1, graph.outDegree("B"));
    assertEquals(2, graph.outDegree("C"));
    assertEquals(1, graph.outDegree("D"));
    assertEquals(1, graph.outDegree("E"));
    assertEquals(1, graph.outDegree("F"));
    assertEquals(0, graph.outDegree("G"));
  }
}
