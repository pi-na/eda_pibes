import org.apache.commons.collections4.bag.HashBag;

public class HashBagWithApache<T> {
    private HashBag<T> hb = new HashBag<>();

    public void add(T value){
        hb.add(value);
    }

    public void remove(T value){
        hb.remove(value);
    }

    public int getCount(T value){
        return hb.getCount(value);
    }
}
