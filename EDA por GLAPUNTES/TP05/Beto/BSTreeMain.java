import java.util.Arrays;

public class BSTreeMain {
    public static void main(String[] args) {
        BSTree<Integer> bt = new BSTree<>();
        bt.insert(34);
        bt.insert(35);
        bt.insert(56);
        bt.insert(86);
        bt.insert(19);
        bt.insert(21);
        bt.insert(2);
        bt.insert(5);
        bt.insert(8);
        System.out.println(bt.getHeight());
        System.out.println(bt.contains(32));
        System.out.println(bt.contains(65));
        System.out.println(bt.getHeight());
        //bt.delete(34);
        //bt.preOrder();
        //bt.delete(65);
        //bt.preOrder();
        bt.printByLevels();
        for(Integer i : bt){
            System.out.println(i);
        }
        System.out.println(bt.testAVL());
        bt.preOrder();
        bt.setTraversal(BSTreeInterface.Traversal.PREORDER);
        for(Integer i : bt){
            System.out.println(i);
        }
        bt.inOrder();
        bt.setTraversal(BSTreeInterface.Traversal.INORDER);
        for(Integer i : bt){
            System.out.println(i);
        }
        bt.postOrder();
        bt.setTraversal(BSTreeInterface.Traversal.POSTODER);
        for(Integer i : bt){
            System.out.println(i);
        }

        System.out.println(Arrays.toString(bt.inrange(0,36).toArray()));
    }
}
