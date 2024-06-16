package E00_Correlatives;

import java.util.List;

public class DiGraphImpl<T> extends GraphImpl<T> {
    @Override
    void addEdge(T head, T tail) {
        List<T> aristas1 = nodos.get(head);
        if (aristas1 == null || !nodos.containsKey(tail)) {
            return;
        }
        aristas1.add(tail);
    }
}
