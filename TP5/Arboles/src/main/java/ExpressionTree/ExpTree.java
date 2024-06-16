import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ExpTree {
    private Map<String, Double> variables = new HashMap<>();
    private Node root;
    private StringBuilder expression = new StringBuilder();
    //TODO Evaluate

    public ExpTree(String infix, Map<String, Double> variables) {
        this.variables = variables;
        Scanner inputScanner = new Scanner(infix).useDelimiter("\\n");
        String line = inputScanner.nextLine();
        inputScanner.close();
        buildTree(line);
    }

    public ExpTree(String infix) {
        // token analyzer
        this(infix, null);
    }

    public ExpTree() {
        System.out.print("Introduzca la expresión en notación infija con todos los parentesis y blancos: ");

        // token analyzer
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        String line = inputScanner.nextLine();
        inputScanner.close();

        buildTree(line);
    }

    private void buildTree(String line) {
        // space separator among tokens
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
        root = new Node(lineScanner);
        lineScanner.close();
    }

    public String preOrder() {
        expression = new StringBuilder();
        preOrderRec(root);
        return getExpression();
    }

    private void preOrderRec(Node node) {
        if (node == null)
            return;
        expression.append(node.data).append(" ");
        preOrderRec(node.left);
        preOrderRec(node.right);
    }


    public String postOrder() {
        expression = new StringBuilder();
        postOrderRec(root);
        return getExpression();
    }

    // fix post order.
    private void postOrderRec(Node node) {
        if (node == null) {
            return;
        }
        if (node.isLeaf())
            expression.append(node.data).append(" ");
        postOrderRec(node.left);
        postOrderRec(node.right);
        if (!node.isLeaf())
            expression.append(node.data).append(" ");
    }

    public String inOrder() {
        expression = new StringBuilder();
        inOrderRec(root);
        return getExpression();
    }

    private void inOrderRec(Node node) {
        if (node == null)
            return;
        if (!node.isLeaf())
            expression.append("( "); // empieza la expresion.
        inOrderRec(node.left);
        expression.append(node.data).append(" ");
        inOrderRec(node.right);
        if (!node.isLeaf()) {
            expression.append(") "); // finaliza la expresion.
        }
    }

    public String getExpression() {
        return expression.toString();
    }

    //add = update
    public void addVariable(String name, double value) {
        variables.put(name, value);
    }

    public double evaluate() {
        return evaluateRec(root);
    }

    private double evaluateRec(Node node) {
        if (node.isLeaf()) {
            if (node.data.matches("[A-Za-z][A-Za-z0-9]+")) {
                return variables.getOrDefault(node.data, 0.0);
            }
            return Double.parseDouble(node.data);
        }

        switch (node.data) {
            case "+":
                return evaluateRec(node.left) + evaluateRec(node.right);
            case "-":
                return evaluateRec(node.left) - evaluateRec(node.right);
            case "*":
                return evaluateRec(node.left) * evaluateRec(node.right);
            case "/":
                return evaluateRec(node.left) / evaluateRec(node.right);
            default:
                return 0;
        }
    }

    static final class Node {
        private String data;
        private Node left, right;

        private Scanner lineScanner;

        public Node(Scanner theLineScanner) {
            lineScanner = theLineScanner;

            Node auxi = buildExpression();
            data = auxi.data;
            left = auxi.left;
            right = auxi.right;

            if (lineScanner.hasNext())
                throw new RuntimeException("Bad expression");
        }

        private Node(String data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        private Node buildExpression() {
            String data;
            // Caso base, no tiene un parentesis, tiene que ser una hoja.
            if (!lineScanner.hasNext("\\(")) {
                if (!lineScanner.hasNext() /*|| !isConstant(data = lineScanner.next())*/) {
                    throw new RuntimeException("Missing or not a constant.");
                }
                data = lineScanner.next();
                return new Node(data, null, null);
            }
            // Caso en el que tiene un parentesis, es una expresion.
            lineScanner.next(); // lo consumo.
            Node leftNode = buildExpression(); // armo la expresion izquierda.

            // Busco al operador.
            if (!lineScanner.hasNext() || !isOperator(data = lineScanner.next()))
                throw new RuntimeException("Missing or Invalid op.");

            Node rightNode = buildExpression(); // armo la expresion derecha.

            // Espero un ).
            if (!lineScanner.hasNext("\\)")) {
                throw new RuntimeException("Bad expression");
            }
            lineScanner.next(); // lo consumo.

            return new Node(data, leftNode, rightNode);
        }

        private boolean isOperator(String c) {
            return c.equals("+") || c.equals("-")
                    || c.equals("*") || c.equals("/")
                    || c.equals("^");
        }

        private boolean isConstant(String string) {
            boolean numeric = true;
            try {
                Double.parseDouble(string);
            } catch (NumberFormatException e) {
                numeric = false;
            }
            return numeric;
        }


    }  // end Node class

}  // end ExpTree