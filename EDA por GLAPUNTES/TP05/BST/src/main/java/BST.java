import java.util.*;
import java.util.function.Function;

public class BST<T extends Comparable<? super T>> implements BSTreeInterface<T> {
    Node<T> root;
    Traversal myTraversal;

    @Override
    public NodeTreeInterface<T> getRoot() {
        return root == null ? null : root;
    }

    @Override
    public void insert(T myData) {
        if (root == null) {
            root = new Node<>(myData);
        } else {
            insertRec(root, myData);
        }
    }

    @Override
    public void delete(T myData) {
        if (myData == null) {
            throw new RuntimeException("No data is provided");
        }
        if (root != null) {
            root = root.delete(myData);
        }
    }

    public boolean containsRec(Node<T> node, T data) {
        if (node == null)
            return false;
        int cmp = node.data.compareTo(data);
        if (cmp == 0)
            return true;
        else if (cmp < 0) // Voy a la derecha.
            return containsRec(node.right, data);
        return containsRec(node.left, data); // Voy a la izquierda.
    }

    @Override
    public boolean contains(T myData) {
        if (myData == null)
            throw new RuntimeException("No data is provided");
        return containsRec(root, myData);
    }

    private void insertRec(Node<T> node, T data) {
        // Caso 1: Actualizo la parte izquierda. Los menores a la izquierda.
        if (node.getLeft() == null && node.getData().compareTo(data) >= 0) {
            node.setLeft(new Node<>(data));
            return;
        }

        // Caso 2: Actualizo la parte derecha. Los mayores a la derecha.
        if (node.getRight() == null && node.getData().compareTo(data) < 0) {
            node.setRight(new Node<>(data));
            return;
        }
        // Defino si recursivamente voy hacia la derecha o izquierda.
        if (node.getData().compareTo(data) >= 0) {
            insertRec(node.left, data);
        } else {
            insertRec(node.right, data);
        }
    }

    @Override
    public void inRange(T min, T max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("Min is greater than max.");
        }
        inRangeRec(root, min, max);
        System.out.println();
    }

    private void inRangeRec(Node<T> node, T min, T max) {
        if (node != null) {
            if (node.left != null && node.data.compareTo(min) >= 0) { //Si el izquierdo es mayor que el minimo...
                inRangeRec(node.left, min, max);
            }
            if (node.data.compareTo(min) >= 0 && node.data.compareTo(max) <= 0) {
                System.out.print(node.data + " ");
            }
            if (node.right != null && node.data.compareTo(max) <= 0) { //Si el derecho es mas grande que el maximo...
                inRangeRec(node.right, min, max);
            }
        }
    }

    /**
     * Es recursivo lo cual consume muchos recursos.
     *
     * @return retorna si es AVL o no el arbol
     */
    @Override
    public boolean testAVL() {
        return root == null || (testAVLRec(root));
    }

    private boolean testAVLRec(NodeTreeInterface<T> node) {
        return node == null || (Math.abs(getHeightRec(node.getLeft()) - getHeightRec(node.getRight())) <= 1 && testAVLRec(node.getLeft()) && testAVLRec(node.getRight()));
    }

    private int getHeightRec(NodeTreeInterface<T> node) {
        return (node == null) ? -1 : (1 + Math.max(getHeightRec(node.getLeft()), getHeightRec(node.getRight())));
    }

    /**
     * ===================================================================
     * Order, iterative.
     * ===================================================================
     */

    @Override
    public void preOrder() {
        preOrderRecursive.apply(root);
        System.out.println();
    }

    private final Function<NodeTreeInterface<T>, Number> preOrderRecursive = (t) -> {
        if (t == null)
            return 0;

        System.out.printf(" %d ",(Integer) t.getData());

        this.preOrderRecursive.apply(t.getLeft());
        this.preOrderRecursive.apply(t.getRight());
        return 0;
    };

    @Override
    public void postOrder() {
        postOrderRecursive.apply(root);
        System.out.println();
    }

    private final Function<NodeTreeInterface<T>, Number> postOrderRecursive = (t) -> {
        if (t == null)
            return 0;

        this.postOrderRecursive.apply(t.getLeft());
        this.postOrderRecursive.apply(t.getRight());
        System.out.printf(" %d ", (Integer) t.getData());
        return 0;
    };

    @Override
    public void inOrder() {
        inOrderRecursive.apply(root);
        System.out.println();
    }

    private final Function<NodeTreeInterface<T>, Number> inOrderRecursive = (t) -> {
        if (t == null)
            return 0;

        this.inOrderRecursive.apply(t.getLeft());
        System.out.printf(" %d ", (Integer) t.getData());
        this.inOrderRecursive.apply(t.getRight());
        return 0;
    };

    @Override
    public int getHeight() {
        return getHeightRec(getRoot());
    }

    @Override
    public void printByLevels() {
        if (root == null) {
            return;
        }
        LinkedList<NodeTreeInterface<T>> queue = new LinkedList<>(Collections.singletonList(root));
        while (!queue.isEmpty()) {
            NodeTreeInterface<T> aux = queue.removeFirst();

            if (aux != null) {
                System.out.print(aux.getData() + " ");
                queue.add(aux.getLeft());
                queue.add(aux.getRight());
            }
        }
        System.out.println();
    }

    /**
     * ===================================================================
     * Order, Iterator.
     * ===================================================================
     */

    @Override
    public void setTraversal(Traversal typeTraversal) {
        this.myTraversal = typeTraversal;
    }

    // 	enum Traversal {INORDER, PREORDER, POSTORDER, BYLEVELS};
    @Override
    public Iterator<T> iterator() {
        switch (myTraversal) {
            case INORDER:
                return inOrderIterator();
            case PREORDER:
                return preOrderIterator();
            case POSTORDER:
                return postOrderIterator();
            case BYLEVELS:
                return byLevelsIterator();
        }
        return null;
    }

    public Iterator<T> byLevelsIterator() {
        return new Iterator<T>() {
            private LinkedList<NodeTreeInterface<T>> queue = new LinkedList<>(Collections.singletonList(root));

            @Override
            public boolean hasNext() {
                return !queue.isEmpty() && root != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                NodeTreeInterface<T> aux = queue.removeFirst();
                if (aux.getLeft() != null)
                    queue.add(aux.getLeft());
                if (aux.getRight() != null)
                    queue.add(aux.getRight());
                return aux.getData();
            }
        };
    }

    public Iterator<T> preOrderIterator() {
        return new Iterator<T>() {
            // Derecha, izquierda y se saca el ultimo que se agrego.
            private LinkedList<NodeTreeInterface<T>> stack = new LinkedList<>(Collections.singletonList(root));

            @Override
            public boolean hasNext() {
                return !stack.isEmpty() && root != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                NodeTreeInterface<T> aux = stack.removeLast();
                // Postergamos derecho.
                if (aux.getLeft() != null) {
                    stack.add(aux.getLeft());
                }
                // Postergamos izquierdo.
                if (aux.getRight() != null) {
                    stack.add(aux.getRight());
                }
                return aux.getData();
            }
        };
    }

    public Iterator<T> postOrderIterator() {
        // TODO: Terminar.
        return new Iterator<T>() {
            private LinkedList<NodeTreeInterface<T>> stack = new LinkedList<>(Collections.singletonList(root));

            @Override
            public boolean hasNext() {
                return !stack.isEmpty() && root != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                NodeTreeInterface<T> aux = stack.removeLast();
                // Postergamos izquierdo.
                if (aux.getLeft() != null) {
                    stack.add(aux.getLeft());
                }
                // Postergamos derecho.
                if (aux.getRight() != null) {
                    stack.add(aux.getRight());
                }
                return aux.getData();
            }
        };
    }

    public Iterator<T> inOrderIterator() {
        // TODO: Terminar.
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return root != null;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    /**
     * ===================================================================
     * Node.
     * ===================================================================
     */

    static private class Node<T extends Comparable<? super T>> implements NodeTreeInterface<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public NodeTreeInterface<T> getLeft() {
            return left;
        }

        @Override
        public NodeTreeInterface<T> getRight() {
            return right;
        }

        private Node<T> delete(T myData) {
            if (myData.compareTo(data) == 0) {
                if (left == null) {
                    return right;
                }
                if (right == null) {
                    return null;
                }
                data = lexiAdjacent(left);
                left = left.delete(data);
            }

            if (myData.compareTo(data) < 0) {
                if (left != null) {
                    left = left.delete(myData);
                }
            } else {
                if (right != null) {
                    right = right.delete(myData);
                }
            }
            return this;
        }

        private T lexiAdjacent(NodeTreeInterface<T> candidate) {
            NodeTreeInterface<T> aux = candidate;
            while (aux.getRight() != null) {
                aux = aux.getLeft();
            }
            return aux.getData();
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
    }
}
