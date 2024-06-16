import java.util.Scanner;

public class ExpTree {

    private Node root;

    public ExpTree(String infija) {
        // token analyzer
        Scanner inputScanner = new Scanner(infija).useDelimiter("\\n");
        String line = inputScanner.nextLine();
        inputScanner.close();
        buildTree(line);
    }

    public ExpTree() {
        System.out.print("Introduzca la expresion en notacion infija con todos los parentesis y blancos: ");
        // token analyzer
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        String line= inputScanner.nextLine();
        inputScanner.close();

        buildTree(line);
    }

    public String preorder(){
        StringBuilder sb = new StringBuilder();
        buildPre(sb, root);
        return sb.toString();
    }

    public String postorder(){
        StringBuilder sb = new StringBuilder();
        buildPost(sb, root);
        return sb.toString();
    }

    public String inorder(){
        StringBuilder sb = new StringBuilder();
        buildIn(sb, root);
        return sb.toString();
    }

    public double eval(){
        double result = 0;
        result = evaluate(root);
        return result;
    }

    private Double evaluate(Node node){
        if(node.left == null || node.right == null){
            return Double.valueOf(node.data);
        }
        return operation(evaluate(node.left), evaluate(node.right), node.data);
    }

    private Double operation(Double value1, Double value2, String operator){
        switch (operator) {
            case ("+"): return value1 + value2;
            case("-"): return value1 - value2;
            case ("*"): return value1 * value2;
            case("/"): return value1 / value2;
            case("^"): return Math.pow(value1, value2);
        }
        return null;
    }

    private void buildPre(StringBuilder sb, Node node){
       if(node.left != null && node.right != null) {
           sb.append(node.data + " ");
           buildPre(sb, node.left);
           buildPre(sb, node.right);
       }
       else {
           sb.append(node.data + " ");
       }
    }

    private void buildPost(StringBuilder sb, Node node){
        if(node.left == null && node.right == null) {
            sb.append(node.data + " ");
        }
        else {
            buildPost(sb, node.left);
            buildPost(sb, node.right);
            sb.append(node.data + " ");
        }
    }

    private void buildIn(StringBuilder sb, Node node){
        if(node.left == null && node.right == null){
            sb.append(node.data + " ");
        }
        else{
            sb.append("(" + " ");
            buildIn(sb, node.left);
            sb.append(node.data + " ");
            buildIn(sb, node.right);
            sb.append(")" + " ");
        }
    }

    private void buildTree(String line)
    {
        // space separator among tokens
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
        root= new Node(lineScanner);
        lineScanner.close();
    }

    static final class Node {
        private String data;
        private Node left, right;

        private Scanner lineScanner;

        public Node(Scanner theLineScanner) {
            lineScanner= theLineScanner;

            Node auxi = buildExpression();
            data= auxi.data;
            left= auxi.left;
            right= auxi.right;

            if (lineScanner.hasNext() )
                throw new RuntimeException("Bad expression");
        }

        private Node(String data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;

        }

        private Node buildExpression() 	{
            if(lineScanner.hasNext("\\(")){
                lineScanner.next();
                Node left = buildExpression();
                if(!lineScanner.hasNext("\\+|-|\\*|/")){
                    throw new IllegalArgumentException("Faltan operadores");
                }
                String aux = lineScanner.next();
                Node right = buildExpression();
                if(lineScanner.hasNext("\\)")){
                    lineScanner.next();
                    return new Node(aux,left,right);
                }
                throw new IllegalArgumentException("Falta parentesis que cierra");
            }
            else if(lineScanner.hasNext("-?[0-9]+(\\.[0-9]+)?")){
                return new Node(lineScanner.next(), null, null);
            }
            throw new IllegalArgumentException("Caracter invalido. Numero faltante");
        }


    }

}  