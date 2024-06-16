import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

public class OpenHashing<K,V> {
    protected int initialLookUpSize = 10;
    protected int loadFactor;
    protected int maxLoadFactor = (int) (initialLookUpSize / 1.5);

    // estática. No crece. Espacio suficiente...
    protected LinkedList<Node<K, V>>[] LookUp = new LinkedList[initialLookUpSize];

    protected Function<? super K, Integer> prehash;

    public OpenHashing(Function<? super K, Integer> mappingFn) {
        prehash = mappingFn;
    }

    protected int hash(K key) {
        if (key == null)
            throw new RuntimeException("No key provided");

        return prehash.apply(key) % LookUp.length;
    }

    protected void rehash() {
        initialLookUpSize *= 2;
        int maxLoadFactor = (int) (initialLookUpSize / 1.5);
        LinkedList<Node<K, V>>[] auxLookUp = LookUp;
        LookUp = new LinkedList[initialLookUpSize];
        int prevLoadFactor = loadFactor;
        for (LinkedList<Node<K, V>> list : auxLookUp) {
            // Protect from rehash recursive.
            loadFactor=0;
            list.forEach(kvNode -> insert(kvNode.key,kvNode.value));
        }
        loadFactor = prevLoadFactor;
    }

    // insert = update
    public void insert(K key, V value) {
        if (loadFactor>=maxLoadFactor)
            rehash();
        if (LookUp[hash(key)]==null) {
            LookUp[hash(key)] = new LinkedList<>();
        }
        Node<K,V> isRepeated = get(key);
        if (isRepeated!=null) {
            isRepeated.value = value;
        }
        else {
            LookUp[hash(key)].addLast(new Node<>(key, value));
            loadFactor++;
        }
    }

    public void delete(K key) {
        int hash = hash(key);
        if (LookUp[hash] == null) {
            return;
        }
        Iterator<Node<K,V>> iter = LookUp[hash(key)].iterator();
        while (iter.hasNext()) {
            Node<K,V> node = iter.next();
            if (node.key.equals(key)) {
                iter.remove();
                loadFactor--;
                return;
            }
        }
        if(LookUp[hash].isEmpty()) {
            LookUp[hash] = null;
        }
    }
    public V getValue(K key) {
        Node<K, V> entry = get(key);
        if (entry == null)
            return null;

        return entry.value;
    }

    protected Node<K, V> get(K key) {
        for (Node<K, V> node : LookUp[hash(key)]) {
            if (node.key.equals(key)) {
                // found.
                return node;
            }
        }
        return null;
    }

    public void dump() {
        for (int rec = 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else
                System.out.printf("slot %d contains %s%n", rec, LookUp[rec]);
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
