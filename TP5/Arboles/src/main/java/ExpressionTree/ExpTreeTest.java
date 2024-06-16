import java.util.HashMap;
import java.util.Map;

public class ExpTreeTest {
    public static void main(String[] args) {
        System.out.println("------------------------------- E01, expresiones ------------------------------------");
        /**
         * Testing expresiones E01:
         */
        ExpTree expTree;
        try {
            expTree = new ExpTree("( ( 2 + 3.5 ) * ( -5 / -1) )");
        } catch (Exception e) {
            System.out.println("1: " + e.getMessage());
        }
        try {
            expTree = new ExpTree("( ( 2 + 3 ) )");
        } catch (Exception e) {
            System.out.println("2: " + e.getMessage());
        }
        try {
            expTree = new ExpTree("( ( 2 + 3.5 ) * -10 )");
        } catch (Exception e) {
            System.out.println("3: " + e.getMessage());
        }
        System.out.println("------------------------------- E03, orden ------------------------------------");
        expTree = new ExpTree("( ( 2 + 3.5 ) * -10 )");
        System.out.println(expTree.preOrder());
        System.out.println(expTree.postOrder());
        System.out.println(expTree.inOrder());
        System.out.println("------------------------------- E04, evaluate ------------------------------------");
        Map<String, Double> variables = new HashMap<>();
        expTree = new ExpTree("( ( 2 + 3.5 ) * -10 )", variables);
        System.out.printf("( ( 2 + 3.5 ) * -10 ) = %.2f%n", expTree.evaluate());
    }
}
