import java.util.Arrays;
import java.util.function.Function;

public class ClosedHashing<K, V> extends Hash<K, V> {

    public ClosedHashing(Function<? super K, Integer> mappingFn) {
        super(mappingFn);
    }

    @Override
    protected Node<K, V> get(K key) {
        int hash = hash(key);

        while (LookUp[hash % initialLookUpSize] != null && !LookUp[hash % initialLookUpSize].key.equals(key)) {
            hash++;
        }

        return LookUp[hash];
    }

    @Override
    public void insert(K key, V value) { // O(n)
        if (loadFactor >= maxLoadFactor) {
            rehash();
        }
        loadFactor++;

        int hash = hash(key);

        while (LookUp[hash % initialLookUpSize] != null && LookUp[hash % initialLookUpSize].occupied) {
            hash++;
        }

        LookUp[hash] = new Node(key, value);
    }

    @Override
    public void delete(K key) {
        loadFactor--;
        int hash = hash(key);

        while (LookUp[hash % initialLookUpSize] != null && !LookUp[hash % initialLookUpSize].key.equals(key)) {
            hash++;
        }
        Node next = LookUp[(hash + 1) % initialLookUpSize];

        if (next == null) {
            LookUp[hash] = null;
        } else {
            LookUp[hash].softDelete();
        }
    }

    @Override
    public void dump() {
        for (int rec = 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.printf("slot %d is empty.%n", rec);
            else if (!LookUp[rec].occupied)
                System.out.printf("slot %d is free but previously had %s.%n", rec, LookUp[rec]);
            else
                System.out.printf("slot %d contains %s.%n", rec, LookUp[rec]);
    }
}