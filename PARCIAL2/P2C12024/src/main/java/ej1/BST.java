package ej1;

import java.util.HashMap;
import java.util.Map;

// acepta repetidos
public class BST<T extends Comparable<? super T>> implements BSTreeInterface<T> {

    private Node root;

    @Override
    public void insert(T myData) {
        if (myData == null)
            throw new RuntimeException("element cannot be null");

        root= insert(root, myData);
    }

    private Node insert(Node currentNode, T myData) {
        if (currentNode == null) {
            return new Node(myData);
        }

        if (myData.compareTo(currentNode.data) <= 0)
            currentNode.left= insert(currentNode.left, myData);
        else
            currentNode.right= insert(currentNode.right, myData);

        return currentNode;
    }


//    La implementación debe hacerse sin delegar en la clase Node (o sea, directamente en BST)
    @Override
    public HashMap<T, Integer> inRange(T lower, T upper) {
        if (lower == null || upper == null)
            throw new RuntimeException("lower and upper cannot be nulls");

        if(lower.compareTo(upper) > 0){
            T aux = lower;
            lower = upper;
            upper = aux;
        }

        HashMap<T, Integer> toReturn = new HashMap<>();

        inRange(root, toReturn, lower, upper);

        return toReturn;
    }

    // lowerLimit INCLUSIVE
    private void inRange(NodeTreeInterface<T> node, Map<T, Integer> result, T lowerLimit, T upperLimit){
        if(node == null) return;

        if(node.getData().compareTo(upperLimit) > 0){ inRange(node.getLeft(), result, lowerLimit, upperLimit); return; }

        if(node.getData().compareTo(lowerLimit) < 0){ inRange(node.getRight(), result, lowerLimit, upperLimit); return; }

        // else el dato pertenece al intervalo
        result.merge(node.getData(), 1, Integer::sum);
        inRange(node.getLeft(), result, lowerLimit, upperLimit);
        inRange(node.getRight(), result, lowerLimit, upperLimit);
    }

    private void inRange(Node node, T lower, T upper, HashMap<T, Integer> map) {
        if (node == null) {	//si no hay ningun elemento, devuelvo un hashmap vacio
            return;
        }

        if (node.data.compareTo(lower) >= 0 && node.data.compareTo(upper) <= 0) {
            map.put(node.data, map.getOrDefault(node.data, 0) + 1);
        }

        if (node.data.compareTo(lower) >= 0) {
            inRange(node.left, lower, upper, map);
        }

        if (node.data.compareTo(upper) < 0) {
            inRange(node.right, lower, upper, map);
        }
    }


    class Node implements NodeTreeInterface<T> {

        private T data;
        private Node left;
        private Node right;

        public Node(T myData) {
            this.data= myData;
        }

        public T getData() {
            return data;
        }
        public NodeTreeInterface<T> getLeft() {
            return left;
        }
        public NodeTreeInterface<T> getRight() {
            return right;
        }

    }

    public static void main(String[] args) {
        BST<Integer> myTree = new BST<>();

        myTree.insert(60);
        myTree.insert(40);
        myTree.insert(20);
        myTree.insert(120);
        myTree.insert(300);
        myTree.insert(500);
        myTree.insert(400);
        myTree.insert(30);
        myTree.insert(450);
        myTree.insert(20);
        myTree.insert(20);
        myTree.insert(22);
        myTree.insert(4);
        myTree.insert(100);
        myTree.insert(110);

        HashMap<Integer, Integer> rta;


        rta= myTree.inRange(1,  10);
        System.out.println(rta);
        System.out.println("EXPECTED: {4: 1}");
        System.out.println("-------------------------------");

        rta = myTree.inRange(23,  91);
        System.out.println(rta);
        System.out.println("EXPECTED: {40=1, 60=1, 30=1}");
        System.out.println("-------------------------------");

        rta = myTree.inRange(23,  400);
        System.out.println(rta);
        System.out.println("EXPECTED: {400=1, 100=1, 40=1, 120=1, 60=1, 300=1, 30=1, 110=1}");
        System.out.println("-------------------------------");

        rta = myTree.inRange(20,  26);
        System.out.println(rta);
        System.out.println("EXPECTED: {20=3, 22=1}");
        System.out.println("-------------------------------");

        rta = myTree.inRange(200,  430);
        System.out.println(rta);
        System.out.println("EXPECTED: {400=1, 300=1}");
        System.out.println("-------------------------------");

        rta = myTree.inRange(30,  20);
        System.out.println(rta);
        System.out.println("EXPECTED: {20:3, 22:1, 30:1}");
        System.out.println("-------------------------------");

        rta = myTree.inRange(121,  200);
        System.out.println(rta);
        System.out.println("EXPECTED: {}");
        System.out.println("-------------------------------");

        rta = myTree.inRange(900,  800);
        System.out.println(rta);
        System.out.println("EXPECTED: {}");
        System.out.println("-------------------------------");
    }



}