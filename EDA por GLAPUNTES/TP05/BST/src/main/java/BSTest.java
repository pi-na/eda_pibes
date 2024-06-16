public class BSTest {
    public static void main(String[] args) {
        System.out.println("---------------------- In,Pre,Post Order ------------------------------");
        BST<Integer> myTree = new BST<>();
        myTree.inOrder(); // -
        myTree.preOrder(); // -
        myTree.postOrder(); // -

        // y este?
        myTree.insert(50);
        myTree.insert(60);
        myTree.insert(80);
        myTree.insert(20);
        myTree.insert(70);
        myTree.insert(40);
        myTree.insert(44);
        myTree.insert(10);
        myTree.insert(40);

        myTree.inOrder(); //  10  20  40  40  44  50  60  70  80
        myTree.preOrder(); // 50  20  10  40  40  44  60  80  70
        myTree.postOrder(); // 10  40  44  40  20  70  80  60  50

        System.out.println(String.format("Height: %d",myTree.getHeight())); // 3

        System.out.println("---------------------- Print by levels ------------------------------");
        myTree = new BST<>();
        myTree.insert(35);
        myTree.insert(74);
        myTree.insert(20);
        myTree.insert(22);
        myTree.insert(55);
        myTree.insert(15);
        myTree.insert(8);
        myTree.insert(27);
        myTree.insert(25);
        myTree.printByLevels(); // 35 20 74 15 22 55 8 27 25

//        System.out.println("----------------------------------------------------");
//        for (Integer data : myTree) {
//            System.out.print(data + " "); // 35 20 74 15 22 55 8 27 25
//        }
//        // Puedo hacerlo múltiples veces…
//        System.out.println("\nUna vez más…\n");
//        myTree.forEach(t -> System.out.print(t + " ")); // 35 20 74 15 22 55 8 27 25

        System.out.println("----------------------- InRange -----------------------------");
        myTree = new BST<>();
        myTree.insert(35);
        myTree.insert(74);
        myTree.insert(20);
        myTree.insert(22);
        myTree.insert(55);
        myTree.insert(15);
        myTree.insert(8);
        myTree.insert(27);
        myTree.insert(25);
        /**
         * InRange
         */
        System.out.println("In range: 20 74");
        myTree.inRange(20, 74);
        System.out.println();
        System.out.println("-----");
        System.out.println("In range: 21 36");
        myTree.inRange(21, 36);
        System.out.println();
        System.out.println("-----");
        System.out.println("In range: 8 55");
        myTree.inRange(8, 55);
        System.out.println();
        System.out.println("-----");
        System.out.println("In range: 21 34");
        myTree.inRange(21, 34);
        System.out.println();
        System.out.println("In range: 40 70");
        myTree.inRange(40, 70);
        System.out.println();


        System.out.println("---------------------- AVL ------------------------------");
        myTree = new BST<>();
        myTree.insert(50);
        myTree.insert(30);
        myTree.insert(70);
        myTree.insert(40);
        myTree.insert(10);
        myTree.insert(80);
        myTree.insert(60);
        System.out.println(String.format("It is%s an AVL tree",
                myTree.testAVL() ? "" : " NOT "));
        myTree = new BST<>();
        myTree.insert(60);
        myTree.insert(50);
        myTree.insert(80);
        myTree.insert(30);
        myTree.insert(70);
        myTree.insert(40);
        myTree.insert(20);

        System.out.println(String.format("It is%s an AVL tree",
                myTree.testAVL() ? "" : " NOT "));
        myTree = new BST<>();
        myTree.insert(60);
        myTree.insert(80);
        myTree.insert(40);
        myTree.insert(30);
        myTree.insert(70);
        myTree.insert(50);
        myTree.insert(20);
        System.out.println(String.format("It is%s an AVL tree",
                myTree.testAVL() ? "" : " NOT "));

    }
}
