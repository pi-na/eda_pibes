import java.util.function.Function;

/*
 *  IMPORTANTE!
 *  En este ejercicio se hace el casteo de un array con generics.
 *  Esto se debe a que no se puede crear un array usando tipos genericos,
 *  Pero se puede crear sin aclararlos y desp hacer un cast.
 */

public class ClosedHashing<K, V> implements IndexParametricService<K, V> {
    final private int initialLookupSize= 10;
    private int collisions = 0;
    private int size = 0;
    private double threshold = 0.8;
    private final int CHUNK_SIZE = 10;

    @SuppressWarnings({"unchecked"})
    private Slot<K,V>[] Lookup= (Slot<K,V>[]) new Slot[initialLookupSize];

    private Function<? super K, Integer> prehash;

    public ClosedHashing( Function<? super K, Integer> mappingFn) {
        if (mappingFn == null)
            throw new RuntimeException("fn not provided");

        prehash= mappingFn;
    }

    // Se ajusta al tamanio de la tabla
    private int hash(K key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");

        return prehash.apply(key) % Lookup.length;
    }

    //  Inserta un par k/v. Si habia algun par con el mismo hash lo piso y sumo un collision.
    public void insertOrUpdate(K key, V data) {
        if (key == null || data == null) {
            String msg= String.format("inserting or updating (%s,%s). ", key, data);
            if (key==null)
                msg+= "Key cannot be null. ";

            if (data==null)
                msg+= "Data cannot be null.";

            throw new IllegalArgumentException(msg);
        }

        //  Si ya habia algo sumo collision. Si no, aumento el size.
        Slot<K, V> existingSlot = Lookup[ hash( key) ];
        if(existingSlot != null){
            collisions++;
        } else this.size++;

        //  Si supere el threshold, aumento el array Lookup, hago rehash
        if( (double) size /Lookup.length > threshold ){
            rehash(Lookup.length + CHUNK_SIZE);
        }

        //  Siempre piso el slot con uno nuevo
        Lookup[ hash( key) ] = new Slot<K, V>(key, data);
    }

    @SuppressWarnings({"unchecked"})
    private void rehash(int newLength){
        //  No lanzo exception pq se exactamente cuando se va a usar pero manejo el caso de q sea invalido
        if(newLength <= Lookup.length) return;

        Slot<K, V>[] newLookup = (Slot<K, V>[]) new Slot[newLength];


    }

    // find or get
    public V find(K key) {
        if (key == null)
            return null;

        Slot<K, V> entry = Lookup[hash(key)];
        if (entry == null)
            return null;

        return entry.value;
    }

    public boolean remove(K key) {
        if (key == null)
            return false;

        // lo encontre?
        if (Lookup[ hash( key) ] == null)
            return false;

        Lookup[ hash( key) ] = null;
        this.size--;
        return true;
    }

    public void dump()  {
        for(int rec= 0; rec < Lookup.length; rec++) {
            if (Lookup[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else
                System.out.println(String.format("slot %d contains %s",rec, Lookup[rec]));
        }
    }


    public int size() {
        return this.size;
    }



    static private final class Slot<K, V>	{
        private final K key;
        private V value;

        private Slot(K theKey, V theValue){
            key= theKey;
            value= theValue;
        }


        public String toString() {
            return String.format("(key=%s, value=%s)", key, value );
        }
    }


    public static void main(String[] args) {
        ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
        myHash.insertOrUpdate(55, "Ana");
        myHash.insertOrUpdate(44, "Juan");
        myHash.insertOrUpdate(18, "Paula");
        myHash.insertOrUpdate(19, "Lucas");
        myHash.insertOrUpdate(21, "Sol");
        myHash.dump();

    }
	
/*	
	public static void main(String[] args) {
		ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
		myHash.insertOrUpdate(55, "Ana");
		myHash.insertOrUpdate(29, "Victor");
		myHash.insertOrUpdate(25, "Tomas");
		myHash.insertOrUpdate(19, "Lucas");
		myHash.insertOrUpdate(21, "Sol");
		myHash.dump();
	}
*/
}