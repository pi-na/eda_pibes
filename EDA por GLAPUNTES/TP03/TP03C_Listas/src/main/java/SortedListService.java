import java.util.Iterator;


public interface SortedListService<T extends Comparable<? super T>> extends Iterable<T> {

    void dump();

    boolean isEmpty();

    void add(T element);

    void delete(T element);

    int size();

    T getMin();

    T getMax();

}