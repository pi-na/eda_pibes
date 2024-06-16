public class TesterE01_2018 {
    public static void main(String[] args) {
        BST<Integer> myTree= new BST<>();
        myTree.insert(80);
        myTree.insert(40);
        myTree.insert(150);
        myTree.insert(20);
        myTree.insert(70);
        myTree.insert(200);
        myTree.insert(50);
        myTree.insert(170);
        myTree.insert(60);
        myTree.insert(170);
        myTree.insert(190);
        myTree.inRange( 35, 170); // 40, 50, 60, 70, 80, 150, 170, 170 y no recorre innecesariamente el árbol.
        myTree.inRange(170, 210); // 170, 170, 190, 200 y no recorre innecesariamente el árbol.
        myTree.inRange(300, 450); // no produce resultados y no recorre innecesariamente el árbol
    }
}
