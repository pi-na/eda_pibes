
public class TestGraphFactory {

  public static Graph undirectedUnweightedGraph() {
    Graph graph = new Graph( false);
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addNode("D");
    graph.addNode("E");
    graph.addNode("F");
    graph.addNode("G");

    graph.addEdge("A", "B");
    graph.addEdge("A", "C");
    graph.addEdge("B", "D");
    graph.addEdge("C", "D");
    graph.addEdge("D", "E");
    graph.addEdge("C", "F");
    graph.addEdge("E", "F");
    graph.addEdge("E", "G");

    return graph;
  }

  public static Graph directedUnweightedGraph() {
    Graph graph = new Graph( true);
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addNode("D");
    graph.addNode("E");
    graph.addNode("F");
    graph.addNode("G");

    graph.addEdge("A", "B");
    graph.addEdge("A", "C");
    graph.addEdge("B", "D");
    graph.addEdge("C", "D");
    graph.addEdge("D", "E");
    graph.addEdge("C", "F");
    graph.addEdge("F", "E");
    graph.addEdge("E", "G");

    return graph;
  }

  public static WeightedGraph directedWeightedGraph() {
    WeightedGraph graph = new WeightedGraph( true);
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addNode("D");
    graph.addNode("E");
    graph.addNode("F");
    graph.addNode("G");

    graph.addEdge("A", "B", 2);
    graph.addEdge("A", "C", 4);
    graph.addEdge("B", "D", 6);
    graph.addEdge("C", "D", 2);
    graph.addEdge("D", "E", 1);
    graph.addEdge("C", "F", 1);
    graph.addEdge("F", "E", 4);
    graph.addEdge("E", "G", 2);

    return graph;
  }

  public static DenseGraph denseGraph() {
    DenseGraph graph = new DenseGraph( false);
    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addNode("D");
    graph.addNode("E");
    graph.addNode("F");
    graph.addNode("G");

    graph.addEdge("A", "B");
    graph.addEdge("A", "C");
    graph.addEdge("B", "D");
    graph.addEdge("C", "D");
    graph.addEdge("D", "E");
    graph.addEdge("C", "F");
    graph.addEdge("E", "F");
    graph.addEdge("E", "G");

    return graph;
  }
}
