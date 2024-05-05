import java.util.Iterator;
import java.util.NoSuchElementException;

// lista simplemente encadenada, no acepta repetidos (false e ignora) ni nulls (exception)
public class SortedLinkedList<T extends Comparable<? super T>> implements SortedListService<T>{
	private Node root;

	//===================================== FUNCIONES ITERATIVAS

	// insert resuelto todo en la clase SortedLinkedList, iterativo
	public boolean insertIterative(T data) {
		
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");

		Node prev= null;
		Node current = root;

		//	Mientras el nodo actual no sea null y los datos del nodo
		//	actual sean anteriores a los que quiero insertar...
		while (current!=null && current.data.compareTo(data) < 0) {
			// avanzo
			prev= current;
			current= current.next;
		}

		// repetido! No inserto.
		if (current!=null && current.data.compareTo(data) == 0) {
			System.err.println(String.format("Insertion failed. %s repeated", data));
			return false;
		}

		//	En este punto, la data a insertar es mayor a la data del nodo actual,
		//	y no estoy intentando insertar repetido.
		Node aux= new Node(data, current);
		if (current == root) {
			// el primero es un caso especial: cambia root
			root= aux;
		}
		else {
			// nodo interno
			prev.next= aux;
		}
		
		return true;
	}

	public boolean removeIterative(T data) {
		Node prev= null;
		Node current = root;
		while (current!=null && current.data.compareTo(data) < 0) {
			// avanzo
			prev= current;
			current= current.next;
		}
		// lo encontre?
		if (current!=null && current.data.compareTo(data) == 0) {
			// borrando
			if (current == root)
				root= root.next;
			else
				prev.next=current.next;
			return true;
		}
		return false;
	}

	//END ===================================== END FUNCIONES ITERATIVAS



	//===================================== FUNCIONES RECURSIVAS, MANEJADAS DESDE LINKEDLIST

	// insertRecursive. La clase SortedLinkedList maneja la insercion, de manera recursiva
	public boolean insertRecursive(T data) {
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");
		
		boolean[] rta = new boolean[1];
		root= insertRec(data, root, rta);
		return rta[0];
	}
	
	//	Inserta de manera recursiva, -desde fuera de la lista- un nuevo nodo
	private Node insertRec(T data, Node current, boolean[] rta) {
		//CASOS BASE:
		//Si current == null, inserto
		if(current == null){
			//Estoy insertando al principio o al final
			rta[0] = true;
			return new Node(data, null);
		}
		//Si current.data == data, NO INSERTO
		if(current.data.compareTo(data) == 0){
			rta[0] = false;
			return current;
		}

		//Si current.data < data, avanzo
		if(current.data.compareTo(data) < 0){
			//Al nodo actual le pongo en next la lista con el dato insertado
			current.next = insertRec(data, current.next, rta);
			return current;
		} else {
			//Si current.data > data, inserto
			rta[0] = true;
			Node aux = new Node(data, current);
			return aux;
		}
	}

	public boolean removeRec(T data) {
		// completar
		return true;

	}

	private Node removeRec(T data, Node current, boolean[] rta) {

		// completar
		return null;
	}

	//END ===================================== END FUNCIONES RECURSIVAS, MANEJADAS DESDE LINKEDLIST


	//START ===================================== FUNCIONES DELEGADAS AL NODO (TIPO PI)

	@Override
	public boolean insert(T data) {
		//	Si la lista esta vacia, inserto directo
		if(root == null) {
			root = new Node(data, null);
			return true;
		}
		//Uso un array para editarlo desde adentro, tipo punteros de pi
		boolean[] rta = new boolean[1];
		//Reemplazo root por el root de la nueva lista con el dato insertado (quizas es la misma lista que antes si es repe)
		root = root.insert(data, rta);
		return rta[0];
	}

	@Override
	public boolean remove(T data){
		if(root == null){
			return false;
		}
		boolean[] rta = new boolean[1];
		root = root.remove(data, rta);
		return rta[0];
	}

	//END ===================================== END FUNCIONES DELEGADAS AL NODO (TIPO PI)

	@Override
	public boolean find(T data) {
		return getPos(data) != -1;
	}


	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		int rta= 0;
		
		Node current = root;

		while (current!=null ) {
			// avanzo
			rta++;
			current= current.next;
		}
		return rta;
	}


	//Printea toda la lista
	@Override
	public void dump() {
		Node current = root;

		while (current!=null ) {
			// avanzo
			System.out.println(current.data);
			current= current.next;
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !  (other instanceof SortedLinkedList) )
			return false;
		
		@SuppressWarnings("unchecked")
		SortedLinkedList<T> auxi = (SortedLinkedList<T>) other;
		
		Node current = root;
		Node currentOther= auxi.root;
		while (current!=null && currentOther != null ) {
			if (current.data.compareTo(currentOther.data) != 0)
				return false;
			
			// por ahora si, avanzo ambas
			current= current.next;
			currentOther= currentOther.next;
		}
		
		return current == null && currentOther == null;
		
	}
	
	// -1 si no lo encontro
	protected int getPos(T data) {
		Node current = root;
		int pos= 0;
		
		while (current!=null ) {
			if (current.data.compareTo(data) == 0)
				return pos;
			
			// avanzo
			current= current.next;
			pos++;
		}
		return -1;
	}
	
	@Override
	public T getMin() {
		if (root == null)
			return null;
		
		return root.data;
	}


	@Override
	public T getMax() {
		
		if (root == null)
			return null;
		
		Node current = root;

		while (current.next !=null ) {
			// avanzo
			current= current.next;
		}
		
		return current.data;
	}

	@Override
	public Iterator<T> iterator() {
		return new SortedLinkedListIterator();
	}

	//END ========================================================	FIN CLASE SORTEDLINKEDLIST

	private class SortedLinkedListIterator implements Iterator<T>{
		private Node current;
		public SortedLinkedListIterator(){
			current = root;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T rta = current.data;
			current = current.next;
			return rta;
		}
	}

	private final class Node {
		private T data;
		private Node next;

		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		public Node insert(T newData, boolean[] rta) {
			//	Ya estaba el dato que me pidieron, retorno
			if (this.data.compareTo(newData) == 0) {
				rta[0] = false;
				return this;
			} else if (this.data.compareTo(newData) > 0) {
				//this.data > newData, INSERTO!
				rta[0] = true;
				Node aux = new Node(newData, this);
				return aux;
			} else if (this.next == null) {
				//this.data < newData y estoy en el final de la lista, inserto adelante
				rta[0] = true;
				Node aux = new Node(newData, null);
				this.next = aux;
				return this;
			} else {
				//this.data < newData y adelante mio hay una lista, inserto en esa lista.
				this.next = next.insert(newData, rta);
				return this;
			}
		}

		public Node remove(T removeData, boolean[] rta){
			if(data.compareTo(removeData) > 0){
				//	El nodo actual es mayor al que quiero remover
				rta[0] = false;
				return this;
			} else if(data.compareTo(removeData) == 0){
				//	El nodo actual es el que quiero remover
				return next;
			} else if( next == null ){
				//	No encontre el elemento
				rta[0] = false;
				return this;
			}
			//else data < removeData, remuevo en la lista de next
			next = next.remove(removeData, rta);
			return this;
		}
	}

	public static void main(String[] args) {
		SortedLinkedList<String> l = new SortedLinkedList<>();

		l.insert("hola"); l.insert("tal"); l.insert("que");
		for (String s : l) {
			System.out.println(s);
		}


		System.out.println("lista " +  (l.isEmpty()? "":"NO") + " vacia");
		System.out.println(l.size() );
		System.out.println(l.getMin() );
		System.out.println(l.getMax() );
		System.out.println();
		
		System.out.println(l.insert("hola"));
		l.dump();
		System.out.println();
		
		System.out.println("lista " +  (l.isEmpty()? "":"NO") + " vacia");
		System.out.println();
		
		System.out.println(l.insert("tal"));
		l.dump();
		System.out.println();
		
		System.out.println(l.insert("ah"));
		l.dump();
		System.out.println();
		
		System.out.println(l.insert("veo"));
		l.dump();
		System.out.println();
		
		System.out.println(l.insert("bio"));
		l.dump();
		System.out.println();
		
		System.out.println(l.insert("tito"));
		l.dump();
		System.out.println();


		System.out.println(l.insert("hola"));
		l.dump();
		System.out.println();
		
		
		System.out.println(l.insert("aca"));
		l.dump();
		System.out.println();

		System.out.println("ELIMINANDO ah, hola, veo");
		l.remove("ah");
		l.remove("hola");
		l.remove("veo");
		l.dump();
		System.out.println();
		
		System.out.println(l.size() );
		System.out.println(l.getMin() );
		System.out.println(l.getMax() );

	}


}
