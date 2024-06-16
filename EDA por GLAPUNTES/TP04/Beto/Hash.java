import java.util.Map;
import java.util.function.Function;

public class Hash<K, V>
{
    private int initialLookupSize= 10;
    private double threshold = 0.75;
    private int usedKeys;

    private Node<K,V>[] LookUp= new Node[initialLookupSize];

    private Function<? super K, Integer> prehash;

    public Hash( Function<? super K, Integer> mappingFn)
    {
        prehash= mappingFn;
        usedKeys = 0;
    }

    private int hash(K key)
    {
        if (key == null)
            throw new RuntimeException("No key provided");

        return prehash.apply(key) % LookUp.length;
    }


    public V getValue(K key)
    {
        Node<K, V> entry = get(key);
        if (entry == null)
            return null;

        return entry.value;
    }

    private Node<K,V> get(K key)
    {
        int hash = hash(key);
        int dim = LookUp.length;
        while (LookUp[hash%dim] != null && (LookUp[hash%dim].key != key || (LookUp[hash%dim].key == key && LookUp[hash%dim].deleted))){
            hash++;
        }
        return LookUp[hash%dim];
    }


    public void insert(K key, V value)
    {
        int hash = hash(key);
        if(LookUp[hash] != null && !LookUp[hash].deleted){
            hash = resolveColition(hash, key);
        }
        LookUp[hash] = new Node<K,V>(key,  value, false);
        usedKeys++;
        if((double)usedKeys / LookUp.length >= threshold){
            rehash();
        }
    }

    private int resolveColition(int hash, K key){
        int firstLogic = -1;
        int i = hash;
        int dim = LookUp.length;
        while(LookUp[i%dim] != null && LookUp[i%dim].key != key ){
            if(LookUp[i%dim].deleted && firstLogic == -1){
                firstLogic = i%dim;
            }
            i++;
        }
        if(firstLogic != -1){
            return firstLogic;
        }
        return i%dim;
    }

    public void delete(K key)
    {
        int hash = hash(key);
        int dim = LookUp.length;
        while(LookUp[hash%dim] != null && LookUp[hash%dim].key != key){
            hash++;
        }
        if(LookUp[hash%dim] != null){
            LookUp[hash%dim].setDeleted(true);
        }
    }

    private void rehash(){
        Node<K,V>[] aux = LookUp;
        LookUp = new Node[aux.length * 2];
        usedKeys = 0;
        for(int i = 0; i < aux.length; i++ ) {
            if (aux[i] != null) {
                insert(aux[i].key, aux[i].value);
            }
        }
    }

    public void dump()
    {
        for(int rec= 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else
                System.out.println(String.format("slot %d contains %s", rec, LookUp[rec]));
    }

    static class Node<K,V>
    {
        final K key;
        V value;
        Boolean deleted;

        Node(K theKey, V theValue, Boolean theDeleted)
        {
            key= theKey;
            value= theValue;
            deleted = theDeleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        public String toString()
        {
            return String.format("key=%s, value=%s, deleted=%s", key, value, deleted );
        }
    }

}