package E00_Correlatives;

public class Correlatives {

    public static boolean canFinishDegree(int qty, int[][] correlativas) {
        // Llenamos el grafo.
        GraphImpl<Integer> graph = new GraphImpl<>();
        for (int i = 1; i < qty; i++) {
            graph.addNode(i);
            for (int[] correlativa : correlativas) {
                graph.addEdge(correlativa[0],correlativa[1]);
            }
        }
        // Ahora deberiamos buscar un ciclo usando DFS/BFS
        return false;
    }
}
