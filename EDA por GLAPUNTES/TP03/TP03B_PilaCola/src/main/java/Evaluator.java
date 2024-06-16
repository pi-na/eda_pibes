import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Evaluator {
    Stack<Double> stack;
    private static final Map<String, Integer> mapping = new HashMap<String, Integer>() {
        {
            put("+", 0);
            put("-", 1);
            put("*", 2);
            put("/", 3);
            put("^", 4);
            put("(", 5);
            put(")", 6);
        }
    };

    private static final boolean[][] precedenceMatrix = {
            // +     -      *     /      ^      (     )
            {true, true, false, false, false, false, true},
            {true, true, false, false, false, false, true},
            {true, true, true, true, false, false, true},
            {true, true, true, true, false, false, true},
            {true, true, true, true, false, false, true},
            {false, false, false, false, false, false, false},
            {true, true, true, true, true, false, false}
    };

    private boolean getPrecedence(String top, String current) {
        Integer topIdx, currentIdx;
        if ((topIdx = mapping.get(top)) == null) {
            throw new RuntimeException(String.format("Top operator %s is invalid.", top));
        }
        if ((currentIdx = mapping.get(current)) == null) {
            throw new RuntimeException(String.format("Current operator %s is invalid.", current));
        }
        return precedenceMatrix[topIdx][currentIdx];
    }

    public Evaluator() {
        stack = new Stack<>();
    }

    public String inToPostFixed() {
        // We start reading from standard output.
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        inputScanner.hasNextLine();
        String line = inputScanner.nextLine();
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        // We'll use a Stack of operators to turn it into a post fixed expression.
        Stack<String> operators = new Stack<>();
        StringBuilder toReturn = new StringBuilder();

        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (token.matches("(-?)[0-9]+(\\.?([0-9]+)*)")) {
                // As we have an operand we add it to the String to return.
                toReturn.append(token).append(" ");
            } else { // We have an operator.
                // If there's already an operator in the stack, we'll need to compare it.
                while (!operators.empty() && getPrecedence(operators.peek(), token)) {
                    toReturn.append(operators.pop()).append(" ");
                }
                if (token.equals(")")) {
                    // We check if there's a ) in the stack.
                    if (!operators.empty() && operators.peek().equals("(")) {
                        operators.pop();
                    } else
                        throw new RuntimeException("Missing ( operator.");
                } else // We push the operator.
                    operators.push(token);
            }
        }
        // Pop those operators with less priority.
        while (!operators.empty()) {
            if (operators.peek().equals("("))
                throw new RuntimeException("Missing ) operator.");
            toReturn.append(operators.pop()).append(" ");
        }
        return toReturn.toString();
    }

    public double evaluate() {
        String expression = inToPostFixed();
        System.out.println("In Post-Fixed: "+expression);
        Scanner lineScanner = new Scanner(expression);
        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            //"(-?)[0-9]+(,?([0-9]+)*)" es un numero
            if (token.matches("(-?)[0-9]+(\\.?([0-9]+)*)")) {
//                System.out.println("Received a number: " + token);
                stack.push(Double.parseDouble(token));
            }
            // "\\*?|-?|\\+?|/?" es una operacion
            else if (token.matches("\\*?|-?|\\+?|/?|\\^?")) {
//                System.out.println("Received an expression.");
                double var2 = stack.pop();
                double var1 = stack.pop();
//                System.out.println("Operation: " + var1 + token + var2);
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
                        break;
                }
            }
        }
        if (!stack.isEmpty()) {
            Double toReturn = stack.pop();
            if (stack.isEmpty())
                return toReturn;
        }
        throw new RuntimeException("Not a valid expression to evaluate.");
    }

}
