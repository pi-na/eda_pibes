import java.util.function.Function;

public class HashBagFromScratch<K> {
    private Hash<K,Integer> h;

    public HashBagFromScratch(Function<? super K, Integer> mappingFn){
        h = new Hash<>(mappingFn);
    }

    public void add(K key){
        Integer result = h.getValue(key);
        if(result == null){
            h.insert(key, 1);
        }
        else{
            h.insert(key, result + 1);
        }
    }

    public void remove(K key){
        Integer result = h.getValue(key);
        if(result != null){
            h.insert(key, result - 1);
            if(h.getValue(key) == 0){
                h.delete(key);
            }
        }
    }

    public int getCount(K key){
        Integer result = h.getValue(key);
        if(result == null){
            return 0;
        }
        return result;
    }


}
