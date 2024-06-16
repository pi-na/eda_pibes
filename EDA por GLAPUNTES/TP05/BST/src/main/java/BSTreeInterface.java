public interface BSTreeInterface<T extends Comparable<? super T>> extends Iterable<T> {
    enum Traversal {INORDER, PREORDER, POSTORDER, BYLEVELS}

    void insert(T myData);

    void delete(T myData);

    boolean contains(T myData);

    void preOrder();

    void postOrder();

    void inOrder();

    NodeTreeInterface<T> getRoot();

    int getHeight();

    void printByLevels();

    void setTraversal(Traversal typeTraversal);

    void inRange(T min, T max);

    public boolean testAVL();
}