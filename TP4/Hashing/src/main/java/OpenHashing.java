import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class OpenHashing<K,V> implements IndexParametricService<K,V> {
    // Tamaño inicial de la tabla de hash
    final private int initialLookupSize= 10;
    // Umbral de carga para rehashing
    final private double threshold = 0.75;
    // Tamaño actual de la tabla
    private int size = 0;
    // Tabla de hash
    @SuppressWarnings({"unchecked"})
    private List<Slot<K,V>>[] lookup = (LinkedList<Slot<K,V>>[]) Array.newInstance(LinkedList.class, initialLookupSize);

    // Función de prehashing
    private Function<? super K, Integer> prehash;

    // Constructor que recibe la función de mapeo
    public OpenHashing( Function<? super K, Integer> mappingFn) {
        if (mappingFn == null)
            throw new RuntimeException("fn not provided");

        prehash= mappingFn;
    }

    // Función de hash
    private int hash(K key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");

        return prehash.apply(key) % lookup.length;
    }

    // Inserta o actualiza un elemento en la tabla
    @Override
    public void insertOrUpdate(K key, V data) {
        // Validación de argumentos
        if (key == null || data == null) {
            throw new IllegalArgumentException("Key and data cannot be null");
        }
        int index;
        Slot<K,V> slot = new Slot<>(key,data);

        // Se verifica si la lista (overflow) en la posición hash(key) existe, si no se crea
        if(lookup[hash(key)]==null)
            lookup[hash(key)] = new LinkedList<>();

        // Se busca el elemento en la lista, si existe se actualiza, sino se añade
        if((index = lookup[hash(key)].indexOf(slot))>=0)
            lookup[hash(key)].set(index, slot);
        else {
            lookup[hash(key)].add(slot);
            size++;
            if((double)size/lookup.length >= threshold)
                rehash();
        }
    }

    // Rehashing de la tabla
    @SuppressWarnings({"unchecked"})
    private void rehash() {
        List<Slot<K,V>>[] aux = lookup;
        lookup = (LinkedList<Slot<K,V>>[]) Array.newInstance(LinkedList.class, lookup.length*2);
        size = 0;
        // Se reinsertan todos los elementos en la nueva tabla
        for (List<Slot<K, V>> list : aux) {
            if(list!=null) {
                for(Slot<K,V> kvSlot : list)
                    insertOrUpdate(kvSlot.key, kvSlot.value);
            }
        }
    }

    // Busca un elemento en la tabla
    @Override
    public V find(K key) {
        if (key == null || lookup[hash(key)]==null)
            return null;

        // Se busca el elemento en la lista correspondiente
        int index = lookup[hash(key)].indexOf(new Slot<K,V>(key));
        return index>=0 ? lookup[hash(key)].get(index).value : null;
    }

    // Elimina un elemento de la tabla
    @Override
    public boolean remove(K key) {
        if(key==null || lookup[hash(key)]==null)
            return false;

        // Se elimina el elemento de la lista correspondiente
        boolean toReturn = lookup[hash(key)].remove(new Slot<K,V>(key));
        // Si la lista queda vacía se setea como null para ahorrar memoria
        if(lookup[hash(key)].size()==0)
            lookup[hash(key)] = null;
        return toReturn;
    }

    // Devuelve el tamaño actual de la tabla
    @Override
    public int size() {
        return size;
    }

    // Muestra el contenido de la tabla
    @Override
    public void dump() {
        for(List<Slot<K,V>> list : lookup)
            if(list!=null) {
                System.out.println(list);
            }
    }

    // Clase interna para representar las ranuras de la tabla
    static private final class Slot<K, V>	{
        private final K key;
        private final V value;

        private Slot(K theKey, V theValue){
            key= theKey;
            value= theValue;
        }

        private Slot(K theKey) {
            this(theKey, null);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Slot)) return false;
            Slot<?, ?> slot = (Slot<?, ?>) o;
            return key.equals(slot.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        public String toString() {
            return String.format("(key=%s, value=%s)", key, value );
        }
    }
}
