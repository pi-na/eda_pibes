import java.util.*;

public class WeightedGraph {

  private final boolean isDirected;
  Map<String, Node> nodes;

  public WeightedGraph(boolean isDirected) {
    this.isDirected = isDirected;
    nodes = new HashMap<>();
  }

  void addNode(String label) {
    nodes.putIfAbsent(label, new Node(label));
  }

  void addEdge(String label1, String label2, double weight) {
    Node node1 = nodes.get(label1);
    Node node2 = nodes.get(label2);
    if (node1 == null || node2 == null) {
      return;
    }
    node1.edges.add(new Edge(node2, weight));
    if (!isDirected) {
      node2.edges.add(new Edge(node1, weight));
    }
  }

  private void unmarkAllNodes() {
    nodes.values().forEach(Node::unmark);
  }

  public void printDijkstra(String startingLabel) {
    unmarkAllNodes();
    nodes.values().forEach(node -> node.cost = Double.MAX_VALUE);

    PriorityQueue<PqNode> queue = new PriorityQueue<>();
    queue.add(new PqNode(nodes.get(startingLabel), 0));

    while (!queue.isEmpty()) {
      PqNode pqNode = queue.remove();
      if (pqNode.node.marked) continue;
      pqNode.node.mark();
      System.out.println(pqNode.node.label + ": " + pqNode.cost);

      for (Edge edge : pqNode.node.edges) {
        double targetNodeCost = pqNode.cost + edge.weight;
        if (targetNodeCost < edge.targetNode.cost) {
          edge.targetNode.cost = targetNodeCost;
          queue.add(new PqNode(edge.targetNode, targetNodeCost));
        }
      }
    }
  }

  class Node {
    String label;
    Set<Edge> edges;
    boolean marked;
    double cost;

    public Node(String label) {
      this.label = label;
      edges = new HashSet<>();
    }

    void mark() {
      marked = true;
    }

    void unmark() {
      marked = false;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node) o;
      return Objects.equals(label, node.label);
    }

    @Override
    public int hashCode() {
      return Objects.hash(label);
    }
  }

  class Edge {
    Node targetNode;
    double weight;

    public Edge(Node targetNode, double weight) {
      this.targetNode = targetNode;
      this.weight = weight;
    }
  }

  class PqNode implements Comparable<PqNode> {
    Node node;
    double cost;

    public PqNode(Node node, double cost) {
      this.node = node;
      this.cost = cost;
    }

    @Override
    public int compareTo(PqNode other) {
      return Double.compare(cost, other.cost);
    }
  }
}
