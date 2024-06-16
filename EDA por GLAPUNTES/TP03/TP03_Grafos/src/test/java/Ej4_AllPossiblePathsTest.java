import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Ej4_AllPossiblePathsTest {

  @Test
  void testAllPossiblePathsWorks() {
    Graph graph = TestGraphFactory.undirectedUnweightedGraph();

    List<List<String>> allPaths = graph.getAllPaths("A", "G");
    assertEquals(4, allPaths.size());

    assertTrue(allPaths.contains(Arrays.asList("A", "B", "D", "E", "G")));
    assertTrue(allPaths.contains(Arrays.asList("A", "B", "D", "C", "F", "E", "G")));
    assertTrue(allPaths.contains(Arrays.asList("A", "C", "D", "E", "G")));
    assertTrue(allPaths.contains(Arrays.asList("A", "C", "F", "E", "G")));
  }
}
