import java.util.ArrayList;
import java.util.Iterator;

public interface BSTreeInterface<T extends Comparable<? super T>> extends Iterable<T> {

    enum Traversal {BYLEVELS, INORDER, POSTODER, PREORDER}

    void setTraversal(Traversal traversal);

    void insert(T myData);

    void delete(T myData);

    boolean contains(T myData);

    void preOrder();

    int getHeight();

    void postOrder();

    void inOrder();

    NodeTreeInterface<T> getRoot();

    ArrayList<T> inrange(T min, T max);

}