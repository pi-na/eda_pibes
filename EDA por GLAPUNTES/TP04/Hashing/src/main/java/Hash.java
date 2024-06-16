
import java.util.function.Function;

//import hash.Hash2.Node;


public class Hash<K, V> {
    protected int initialLookUpSize = 10;
    protected int loadFactor;
    protected int maxLoadFactor = (int) (initialLookUpSize / 1.5);

    // estática. No crece. Espacio suficiente...
    protected Node<K, V>[] LookUp = new Node[initialLookUpSize];

    protected Function<? super K, Integer> prehash;

    public Hash(Function<? super K, Integer> mappingFn) {
        prehash = mappingFn;
    }

    // ajuste al tamaño de la tabla
    protected int hash(K key) {
        if (key == null)
            throw new RuntimeException("No key provided");

        return prehash.apply(key) % LookUp.length;
    }

    public V getValue(K key) {
        Node<K, V> entry = get(key);
        if (entry == null)
            return null;

        return entry.value;
    }

    protected Node<K, V> get(K key) {
        return LookUp[hash(key)];
    }

    // insert = update
    public void insert(K key, V value) {
        if (LookUp[hash(key)] == null) {
            loadFactor++;
        }
        LookUp[hash(key)] = new Node<>(key, value);
        if (loadFactor >= maxLoadFactor)
            rehash();
    }

    protected void rehash() {
        initialLookUpSize *= 2;
        int maxLoadFactor = (int) (initialLookUpSize / 1.5);
        Node<K, V>[] tmpLookUp = new Node[initialLookUpSize];
        for (Node<K, V> node : LookUp) {
            if (node != null)
                tmpLookUp[hash(node.key)] = node;
        }
        LookUp = tmpLookUp;
    }

    public void delete(K key) {
        LookUp[hash(key)] = null;
    }

    public void dump() {
        for (int rec = 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else
                System.out.println(String.format("slot %d contains %s", rec, LookUp[rec]));
    }

    static protected class Node<K, V> {
        final K key;
        V value;
        boolean occupied;

        Node(K theKey, V theValue) {
            key = theKey;
            value = theValue;
            occupied = true;
        }

        public void softDelete() {
            occupied = false;
        }

        public String toString() {
            return String.format("key=%s, value=%s", key, value);
        }
    }

}