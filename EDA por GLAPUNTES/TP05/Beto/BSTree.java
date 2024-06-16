import javax.swing.plaf.IconUIResource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BSTree<T extends Comparable<? super T>> implements BSTreeInterface<T>{
    private Node<T> root;
    private int height = 0;
    private Traversal t = Traversal.BYLEVELS;

    @Override
    public void setTraversal(Traversal traversal) {
       t = traversal;
    }

    public void printByLevels(){
        if(root == null) {
            return;
        }
        LinkedList<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node<T> auxi = queue.removeFirst();
            if(auxi != null){
                System.out.println(auxi.data);
                queue.add(auxi.getLeft());
                queue.add(auxi.getRight());
            }
        }
    }
    @Override
    public void insert(T myData) {
        if(root == null){
            root = new Node<>(myData, null, null);
            return;
        }
        root.insert(myData);
    }

    @Override
    public void delete(T myData) {
        root = deleteRec(root, myData);
    }

    private Node<T> deleteRec(Node<T> node, T myData){
        if(node == null){
            return node;
        }
        if(node.data.compareTo(myData) > 0){
            node.left = deleteRec(node.left, myData);
            return node;
        }
        if(node.data.compareTo(myData) < 0){
            node.right = deleteRec(node.right, myData);
            return node;
        }
        if(node.getLeft() == null && node.getRight() == null){
            return null;
        }
        if(node.getLeft() != null && node.getRight() == null){
            return node.left;
        }
        if(node.getRight() != null && node.getLeft() == null){
            return node.right;
        }
        Node<T> searched = searchNode(node.left);
        node.data = searched.data;
        node.left = deleteRec(node.left, searched.data);
        return node;
    }

    private Node<T> searchNode(Node<T> node){
        if(node.getRight() == null){
            return node;
        }
        return searchNode(node.right);
    }

    @Override
    public Iterator<T> iterator() {
        switch (t){
            case BYLEVELS: return new BSTreeIteratorByLevels();
            case INORDER: return new BSTreeIteratorIn();
            case POSTODER: return new BSTreeIteratorPost();
            case PREORDER: return new BSTreeIteratorPre();
        }
        return null;
    }

    public boolean testAVL(){
        if(root == null){
            return true;
        }
        int result = testAVLRec(root);
        return result != -1;
    }

    private int testAVLRec(Node<T> node){
        if(node == null){
            return 0;
        }
        int result1 = testAVLRec(node.getLeft());
        int result2 = testAVLRec(node.getRight());
        if(result1 != -1 && result2 != -1 && Math.abs(result1 - result2) <= 1){
            return Math.max(result1, result2) + 1;
        }
        return -1;
    }

    @Override
    public int getHeight() {
        return getHeightRec(root);
    }

    private int getHeightRec(Node<T> node){
        if(node.getLeft() != null && node.getRight() != null){
            return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
        }
        if(node.getLeft() != null){
            return 1 + getHeightRec(node.left);
        }
        if(node.getRight() != null){
            return 1 + getHeightRec(node.right);
        }
        return 0;

    }

    @Override
    public boolean contains(T myData) {
        Node<T> current = root;
        while(current != null && myData.compareTo(current.data) != 0){
            if (myData.compareTo(current.data) == 1){
                current = current.right;
            }
            else{
                current = current.left;
            }
        }
        return current != null;
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public void preOrder() {
        StringBuilder sb = new StringBuilder();
        getPre(sb, root);
        System.out.println(sb.toString());
    }

    private void getPre(StringBuilder sb, Node<T> node){
        if(node.left == null && node.right == null){
            sb.append(node.getData() + " ");
            return;
        }
        sb.append(node.getData() + " ");
        if(node.left != null){
            getPre(sb, node.left);
        }
        if(node.right != null){
            getPre(sb, node.right);
        }
    }

    @Override
    public void inOrder() {
        StringBuilder sb = new StringBuilder();
        getIn(sb, root);
        System.out.println(sb.toString());
    }

    private void getIn(StringBuilder sb, Node<T> node){
        if(node.left == null && node.right == null){
            sb.append(node.getData() + " ");
            return;
        }
        if(node.left != null){
            getIn(sb, node.left);
        }
        sb.append(node.getData() + " ");
        if(node.right != null){
            getIn(sb, node.right);
        }
    }

    @Override
    public void postOrder() {
        StringBuilder sb = new StringBuilder();
        getPost(sb, root);
        System.out.println(sb.toString());
    }

    private void getPost(StringBuilder sb, Node<T> node){
        if(node.left == null && node.right == null){
            sb.append(node.getData() + " ");
            return;
        }
        if(node.left != null){
            getPost(sb, node.left);
        }
        if(node.right != null){
            getPost(sb, node.right);
        }
        sb.append(node.getData() + " ");
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<T> inrange(T min, T max) {
        ArrayList<T> a = new ArrayList<>();
        inrangeRec(root, min, max, a );
        return a;
    }

    private void inrangeRec(Node<T> node, T min, T max, ArrayList<T> a){
        if(node.left == null && node.right == null){
            if(node.data.compareTo(min) >= 0 && node.data.compareTo(max) <= 0) {
                a.add(node.data);
            }
            return;
        }
        if(node.left != null) {
            if (node.getData().compareTo(min) != -1) {
                inrangeRec(node.left, min, max, a);
            }
        }
        if(node.data.compareTo(min) >= 0 && node.data.compareTo(max) <= 0) {
            a.add(node.data);
        }
        if(node.right != null){
            if(node.getData().compareTo(max) != 1) {
                inrangeRec(node.right, min, max, a);
            }
        }
    }

    static private class Node<T extends Comparable<? super T>> implements NodeTreeInterface<T>{
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data, Node<T> left, Node<T> right){
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private Node (T data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        private void insert(T myData){
           if(data.compareTo(myData) >= 0){
               if(left == null){
                   left = new Node<T>(myData);
               }
               else{
                   left.insert(myData);
               }
           }
           else{
               if(right == null){
                   right = new Node<T>(myData);
               }
               else{
                   right.insert(myData);
               }
           }
        }


        @Override
        public T getData() {
            return data;
        }

        @Override
        public Node<T> getLeft() {
            return left;
        }

        @Override
        public Node<T> getRight() {
            return right;
        }
    }


    private class BSTreeIteratorByLevels implements Iterator<T> {
        LinkedList<Node<T>> queue;
        
        public BSTreeIteratorByLevels(){
            queue = new LinkedList<>();
            if(root != null){
                queue.add(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
           if (!hasNext()){
               throw new NoSuchElementException();
            }
            Node<T> auxi = queue.removeFirst();
            if(auxi.getLeft() != null){
                queue.add(auxi.getLeft());
            }
            if(auxi.getRight() != null){
                queue.add(auxi.getRight());
            }
            return auxi.data;
        }
    }

    private class BSTreeIteratorPre implements Iterator<T>{
        LinkedList<Node<T>> a = new LinkedList<>();

        public BSTreeIteratorPre(){
            if(root != null){
                a.add(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !a.isEmpty();
        }

        @Override
        public T next() {
            if(hasNext()){
                Node<T> auxi = a.removeFirst();
                if(auxi.right != null){
                    a.addFirst(auxi.right);
                }
                if(auxi.left != null){
                    a.addFirst(auxi.left);
                }
                return auxi.data;
            }
            throw new NoSuchElementException();
        }

        //@Override
        //public T next() {
          //  if(hasNext()) {
            //    Node<T> auxi = a.removeFirst();
              //  while(auxi == null && hasNext()) {
                //    auxi = a.removeLast();
                //}
                //a.addFirst(auxi.getLeft());
                //a.add(auxi.getRight());
               // return auxi.data;
            //}
            //throw new NoSuchElementException();
        //}


    }

    private class BSTreeIteratorPost implements Iterator<T>{
        LinkedList<Node<T>> nodes = new LinkedList<>();
        LinkedList<Node<T>> marked = new LinkedList<>();

        public BSTreeIteratorPost(){
            if(root != null) {
                nodes.add(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Node<T> current = nodes.getFirst();
            while(!(marked.contains(current) || isLeaf(current)) ){
                if(current.getRight() != null){
                    nodes.addFirst(current.getRight());
                }
                if(current.getLeft() != null){
                    nodes.addFirst(current.getLeft());
                }
                marked.add(current);
                current = nodes.getFirst();
            }
            marked.remove(current);
            nodes.removeFirst();
            return current.data;
        }

        private boolean isLeaf(Node<T> node){
            return node.getLeft() == null && node.getRight() == null;
        }

    }

    private class BSTreeIteratorIn implements Iterator<T>{
        LinkedList<Node<T>> a = new LinkedList<>();

        public BSTreeIteratorIn(){
           Node<T> n = root;
           while(n != null){
               a.add(n);
               n = n.left;
           }
        }

        @Override
        public boolean hasNext() {
            return !a.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> ret = a.removeLast();
            if(ret.right != null){
                a.add(ret.right);
                Node<T> aux = ret.right.left;
                while(aux != null){
                    a.add(aux);
                    aux = aux.left;
                }

            }
            return ret.data;
        }


    }
}
