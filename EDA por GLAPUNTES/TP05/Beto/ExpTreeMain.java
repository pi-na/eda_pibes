public class ExpTreeMain {
    public static void main(String[] args) {
        ExpTree et = new ExpTree("( ( 2 + 3.5 ) * ( -10 + 56 ) )");
        System.out.println(et.preorder());
        System.out.println(et.postorder());
        System.out.println(et.inorder());
        System.out.println(et.eval());
    }
}
