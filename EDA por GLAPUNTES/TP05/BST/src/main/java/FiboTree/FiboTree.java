package FiboTree;

public class FiboTree {

    private final Node root;
    private final int n;

    public Node getRoot() {
        return root;
    }

    public FiboTree(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Argument must be positive");
        }
        root = fiboTreeRec(n);
        this.n = n;
    }

    private Node fiboTreeRec(int n) {
        if (n == 0) {
            return null;
        }
        Node node = new Node("*");
        if (n == 1) {
            return node;
        }
        node.left = fiboTreeRec(n - 1);
        node.right = fiboTreeRec(n - 2);
        return node;
    }

    public int getHeight() {
        return n - 1;
    }

    private class Node implements NodeFiboInterface {
        private String data;
        private Node left;
        private Node right;

        public Node(String data) {
            this.data = data;
        }

        @Override
        public String getData() {
            return data;
        }

        @Override
        public NodeFiboInterface getLeft() {
            return left;
        }

        @Override
        public NodeFiboInterface getRight() {
            return right;
        }
    }

}
