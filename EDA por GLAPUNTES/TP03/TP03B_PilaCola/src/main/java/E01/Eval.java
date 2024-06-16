package E01;
import java.util.Scanner;
import java.util.Stack;
public class Eval {
    Stack<Double> stack;

    public Eval() {
        stack = new Stack<>();
    }

    public double evaluate() {
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        inputScanner.hasNextLine();
        String line = inputScanner.nextLine();
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (token.matches("(-?)[0-9]+(\\.?([0-9]+)*)")) {
                stack.push(Double.parseDouble(token));
            }
            else if (token.matches("\\*?|-?|\\+?|/?|\\^?")) {
                if(stack.isEmpty())
                    throw new RuntimeException("Missing operands");
                double var2 = stack.pop();
                if(stack.isEmpty())
                    throw new RuntimeException("Missing operands");
                double var1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(var1 + var2);
                        break;
                    case "-":
                        stack.push(var1 - var2);
                        break;
                    case "/":
                        stack.push(var1 / var2);
                        break;
                    case "*":
                        stack.push(var1 * var2);
                        break;
                    case "^":
                        stack.push(Math.pow(var1, var2));
                }
            }
            else
                throw new RuntimeException("Invalid operator");
        }
        if (!stack.isEmpty()) {
            double toReturn = stack.pop();
            if(!stack.isEmpty())
                throw new RuntimeException("Missing operator");
            return toReturn;
        }
        return Double.MAX_VALUE;
    }
}
