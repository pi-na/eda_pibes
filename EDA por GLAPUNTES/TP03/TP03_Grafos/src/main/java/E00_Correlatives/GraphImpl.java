package E00_Correlatives;

import java.util.*;

public class GraphImpl<T> {

    Map<T, List<T>> nodos;

    public GraphImpl() {
        nodos = new HashMap<>();
    }

    void addNode(T label) {
        nodos.putIfAbsent(label, new ArrayList<>());
    }

    void addEdge(T label1, T label2) {
        List<T> aristas1 = nodos.get(label1);
        List<T> aristas2 = nodos.get(label2);
        if (aristas1 == null || aristas2 == null) {
            return;
        }
        aristas1.add(label2);
        aristas2.add(label1);
    }

}