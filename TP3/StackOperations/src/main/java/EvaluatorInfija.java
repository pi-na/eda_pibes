import java.util.*;

/*
* ESTA CLASE RECIBE UN FORMULA EN NOTACION INFIJA Y LA PASA A POSTFIJA PARA LUEGO EVALUARLA
* */

public class EvaluatorInfija {
	private Scanner scannerLine;

	private String getUserInputLine(){
		Scanner input = new Scanner(System.in).useDelimiter("\\n");
		System.out.print("Introduzca la expresión en notación infija: ");
		String line = input.nextLine();
		input.close();
		return line;
	}

	public Double evaluate()
	{
		String exp = infijaToPostfija();
		Scanner scanner = new Scanner(exp);	// TODO cambiar el nombre para q no coincida con la variable de instancia this.scannerLine
		if ( !scanner.hasNext() ) throw new RuntimeException("No hay nada para evaluar");

		//	Codigo duplicado de EvaluatorPostfija
		Stack<Double> auxi= new Stack<Double>();
		while( scanner.hasNext() )
		{
			String s = scanner.next();

			if ( isOperand(s) )
			{
				auxi.push(Double.valueOf(s));
			}
			else
			{	// operador o error
				if (! isOperator(s) )
					throw new RuntimeException("invalid operator " + s);

				Double operand2;
				if (auxi.empty())
					throw new RuntimeException("operand missing");
				else
					operand2= auxi.pop();

				Double operand1;
				if (auxi.empty())
					throw new RuntimeException("operand missing");
				else
					operand1= auxi.pop();

				auxi.push(  eval(s, operand1 , operand2) );
			}
		}
		double rta= auxi.pop();
		if (auxi.empty())
			return rta;

		throw new RuntimeException("operator missing");
	}

	//	Metodo duplicado de EvaluatorPostfija
	private double eval(String op, double val1, double val2)
	{
		switch (op)
		{
			case "+": return val1 + val2;
			case "-": return val1 - val2;
			case "*": return val1 * val2;
			case "/": return val1 / val2;
			case "^": return Math.pow(val1, val2);
		};

		throw new RuntimeException("invalid operator" +  op);
	}

	// Si op1 > op2, op1 precede y se retorna true
	// si op1 <= op2, op2 precede y se retorna false
	private boolean precedes(String op1, String op2){
		//	Exponente contra exponente asocia A DERECHA, comportamiento inverso del habitual. Lo hardcodeo.
		if(Objects.equals(op1, "^") && Objects.equals(op2, "^")) return true;
		return precedenceMap.get(new Couple<>(op1, op2));
	}

	private boolean isOperand(String s)
	{
		try
		{
			Double.valueOf(s);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	private boolean isOperator(String s)
	{
		return s.matches("\\+|-|\\*|/|\\^|\\(|\\)");
	}

	private String infijaToPostfija() {
		String inputLine = getUserInputLine();
		this.scannerLine = new Scanner(inputLine).useDelimiter("\\s+");

		StringBuilder postfija= new StringBuilder();
		Stack<String> opStack= new Stack<String>();
		String currentToken;

		while( scannerLine.hasNext() ) {
			currentToken = scannerLine.next();
			if (isOperand(currentToken)){
				postfija.append(currentToken + " ");
			}
			else if (isOperator(currentToken)){
				if(opStack.isEmpty()) opStack.push(currentToken);
				else {
					// Mientras que current <= tope
					while(!opStack.isEmpty() && precedes(opStack.peek(), currentToken)){
						postfija.append(opStack.pop() + " ");
					}
					if(currentToken.equals(")")){
						if(!opStack.isEmpty() && opStack.peek().equals("(")) opStack.pop();
						else throw new IllegalArgumentException("Falta (");
					} else opStack.push(currentToken);
				}
			} else
				throw new IllegalArgumentException("ERROR! NO ES UN OPERADOR O UN OPERANDO: " + currentToken);
		}

		while(!opStack.isEmpty()){
			if( Objects.equals(opStack.peek(), "(") ) throw new IllegalArgumentException("Falta )");
			postfija.append(opStack.pop() + " ");
		}

		System.out.println("Postfija= " + postfija);
		return postfija.toString();
	}		

	
	public static void main(String[] args) {

		EvaluatorInfija e = new EvaluatorInfija();
		System.out.println(e.evaluate());

	}

	private static final Map<Couple<String>, Boolean> precedenceMap = new HashMap<>(){
		{
			// Parejas para el operador +
			put(new Couple<>("+", "+"), true);
			put(new Couple<>("+", "-"), true);
			put(new Couple<>("+", "*"), false);
			put(new Couple<>("+", "/"), false);
			put(new Couple<>("+", "^"), false);
			put(new Couple<>("+", "("), false);
			put(new Couple<>("+", ")"), true);

			// Parejas para el operador -
			put(new Couple<>("-", "+"), true);
			put(new Couple<>("-", "-"), true);
			put(new Couple<>("-", "*"), false);
			put(new Couple<>("-", "/"), false);
			put(new Couple<>("-", "^"), false);
			put(new Couple<>("-", "("), false);
			put(new Couple<>("-", ")"), true);

			// Parejas para el operador *
			put(new Couple<>("*", "+"), true);
			put(new Couple<>("*", "-"), true);
			put(new Couple<>("*", "*"), true);
			put(new Couple<>("*", "/"), true);
			put(new Couple<>("*", "^"), false);
			put(new Couple<>("*", "("), false);
			put(new Couple<>("*", ")"), true);

			// Parejas para el operador /
			put(new Couple<>("/", "+"), true);
			put(new Couple<>("/", "-"), true);
			put(new Couple<>("/", "*"), true);
			put(new Couple<>("/", "/"), true);
			put(new Couple<>("/", "^"), false);
			put(new Couple<>("/", "("), false);
			put(new Couple<>("/", ")"), true);

			// Parejas para el operador ^
			put(new Couple<>("^", "+"), true);
			put(new Couple<>("^", "-"), true);
			put(new Couple<>("^", "*"), true);
			put(new Couple<>("^", "/"), true);
			put(new Couple<>("^", "^"), true);
			put(new Couple<>("^", "("), false);
			put(new Couple<>("^", ")"), true);

			// Parejas para el paréntesis abierto (
			put(new Couple<>("(", "("), false);
			put(new Couple<>("(", "+"), false);
			put(new Couple<>("(", "-"), false);
			put(new Couple<>("(", "*"), false);
			put(new Couple<>("(", "/"), false);
			put(new Couple<>("(", "^"), false);
			put(new Couple<>("(", ")"), false); // La precedencia es irrelevante aquí porque se cierra el paréntesis

			// Parejas para el paréntesis cerrado )
			put(new Couple<>(")", "("), true);
			put(new Couple<>(")", "+"), true);
			put(new Couple<>(")", "-"), true);
			put(new Couple<>(")", "*"), true);
			put(new Couple<>(")", "/"), true);
			put(new Couple<>(")", "^"), true);
			put(new Couple<>(")", ")"), false);
		}
	};

}