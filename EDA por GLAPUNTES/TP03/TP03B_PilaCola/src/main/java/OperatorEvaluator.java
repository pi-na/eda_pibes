import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class OperatorEvaluator {
    Stack<Double> stack;
    private static final Map<String, Integer> variables = new HashMap<String, Integer>() {
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
        if ((topIdx = variables.get(top)) == null) {
            throw new RuntimeException(String.format("Top operator %s is invalid.", top));
        }
        if ((currentIdx = variables.get(current)) == null) {
            throw new RuntimeException(String.format("Current operator %s is invalid.", current));
        }
        return precedenceMatrix[topIdx][currentIdx];
    }

    public OperatorEvaluator() {
        stack = new Stack<>();
    }


    private String infijaToPostfija(){
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        String input = inputScanner.next();
        Scanner scannerLine = new Scanner(input).useDelimiter("\\s+");
        StringBuilder toReturn = new StringBuilder();

        Stack<String> operators = new Stack<>();

        while (scannerLine.hasNext()){
            String token = scannerLine.next();

            if(isVariable(token))
                toReturn.append(String.format("%s ", variables.get(token)));
            else if(isOperand(token)){
                toReturn.append(String.format("%s ", token));
            }
            else {
                while (!operators.isEmpty() && getPrecedence(operators.peek(), token)){
                    toReturn.append(String.format("%s ", operators.pop()));
                }
                if(token.equals(")")){
                    if(!operators.isEmpty() && operators.peek().equals("("))
                        operators.pop();
                    else
                        throw new RuntimeException("Missing (");
                }
                else
                    operators.push(token);
            }
        }
        while (!operators.isEmpty()) {
            if (operators.peek().equals("("))
                throw new RuntimeException("Illegal expression, too many (");
            toReturn.append(String.format("%s ", operators.pop()));
        }
        return toReturn.toString();
    }

    public double evaluate() {
        System.out.println("Introduzca la expresión en notación infija: ");

        Scanner postFijaScanner = new Scanner(infijaToPostfija()).useDelimiter("\\s+");

        if(!postFijaScanner.hasNext())
            throw new RuntimeException("No hay nada para evaluar");

        while (postFijaScanner.hasNext()) {
            String token = postFijaScanner.next();
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

    private boolean isVariable(String c) {
        return c.equals("+") || c.equals("-")
                || c.equals("*") || c.equals("/")
                || c.equals("^");
    }

    private boolean isOperand(String string) {
        boolean numeric = true;
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }

}
