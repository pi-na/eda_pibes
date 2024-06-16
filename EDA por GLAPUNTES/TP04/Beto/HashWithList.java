import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

public class HashWithList<K,V> {
    private int initialLookupSize= 10;
    private double threshold = 0.75;
    private int usedKeys;

    private ArrayList<Node<K,V>>[] LookUp= new ArrayList[initialLookupSize];

    private Function<? super K, Integer> prehash;

    public HashWithList( Function<? super K, Integer> mappingFn)
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
        if(LookUp[hash%dim] != null){
            int i = 0;
            while(i != LookUp[hash%dim].size()){
                if(LookUp[hash%dim].get(i).key == key){
                    return LookUp[hash%dim].get(i);
                }
                i++;
            }
        }
        return null;
    }


    public void insert(K key, V value)
    {
        int hash = hash(key);
        if(LookUp[hash] != null){
            resolveColition(hash, key, value);
        }
        else{
            LookUp[hash] = new ArrayList<>();
            LookUp[hash].add(new Node<>(key, value));
        }
        usedKeys++;
        if((double)usedKeys / LookUp.length >= threshold){
            rehash();
        }
    }

    private void resolveColition(int hash, K key, V value){
        int dim = LookUp.length;
        boolean found = false;
        int i = 0;
        while( i != LookUp[hash%dim].size() && !found){
            if(LookUp[hash%dim].get(i).key == key){
                LookUp[hash%dim].get(i).value = value;
                found = true;
            }
            i++;
        }
        if(!found){
            LookUp[hash%dim].add(new Node<>(key,value));
        }
    }

    public void delete(K key)
    {
        int hash = hash(key);
        int dim = LookUp.length;
        boolean found = false;
        if(LookUp[hash%dim] != null){
            int i = 0;
            while( i != LookUp[hash%dim].size() && !found){
                if(LookUp[hash%dim].get(i).key == key) {
                    LookUp[hash % dim].remove(i);
                    usedKeys--;
                    found = true;
                }
                i++;
            }
            if(LookUp[hash%dim].isEmpty()){
                LookUp[hash%dim] = null;
            }
        }
    }

    private void rehash(){
        ArrayList<Node<K,V>>[] aux= LookUp;
        LookUp = new ArrayList[initialLookupSize*=2];
        usedKeys = 0;
        int i = 0;
        while( i < aux.length){
            int j = 0;
            if(aux[i] != null) {
                while (j < aux[i].size()) {
                    insert(aux[i].get(j).key, aux[i].get(j).value);
                    j++;
                }
            }
            i++;
        }
    }

    public void dump()
    {
        for(int rec= 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else
                System.out.println(String.format("slot %d contains %s", rec, Arrays.toString(LookUp[rec].toArray()) ));
    }

    static class Node<K,V>
    {
        final K key;
        V value;

        Node(K theKey, V theValue)
        {
            key= theKey;
            value= theValue;
        }
        public String toString()
        {
            return String.format("key=%s, value=%s", key, value);
        }
    }

}
