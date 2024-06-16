import java.util.Scanner;
import java.util.Stack;

public class stackTest {
    public static void main(String[] args) {
        System.out.print("Enter expression: ");
        Evaluator evaluator = new Evaluator();
        double ans = evaluator.evaluate();
        try {
            System.out.println("Answer: " + ans);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
