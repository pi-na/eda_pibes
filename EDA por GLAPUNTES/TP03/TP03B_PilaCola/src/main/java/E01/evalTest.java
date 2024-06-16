package E01;

public class evalTest {
    public static void main(String[] args) {
        try {
            System.out.print("Enter post-fixed expression: ");
            Eval evaluator = new Eval();
            double ans = evaluator.evaluate();
            try {
                System.out.println("Answer: " + ans);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
