import java.util.Iterator;
import java.util.NoSuchElementException;

//	Para esta lista, uso solo los iterative!
public class SortedLinkedListWithHeader<T extends Comparable<? super T>> implements SortedListService<T>{
	private Node root;
	private int size;
	private Node last;

	//===================================== FUNCIONES ITERATIVAS

	// insert resuelto todo en la clase SortedLinkedList, iterativo
	public boolean insert(T data) {
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

		//	Si llego a este punto, estoy en condiciones de insertar.

		Node aux= new Node(data, current);
		// el primero es un caso especial: cambia root (y last)
		if (current == root) {
			root = aux;
			last = root;
		} else {
			//	Si current.data > dataInsertar, Inserto detras del current. Es decir, entre prev y current!
			prev.next = aux;
			if(current == null){
				//	Si current == null estoy agregando un nuevo last (y le estoy pasando null para su cola)!
				last = aux;
			}
		}
		size++;
		
		return true;
	}

	public boolean remove(T data) {
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
			size--;
			if (current == root)
				root= root.next;
			else {
				if(current.next == null) {
					//Si estoy removiendo el last, actualizo para que ahora last sea prev
					last = prev;
				}
				prev.next = current.next;	//Si removi el last, le paso null a prev.next (efectivamente haciendolo last)
			}
			return true;
		}
		return false;
	}

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
		return size;
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
		if (other == null || !  (other instanceof SortedLinkedListWithHeader) )
			return false;
		
		@SuppressWarnings("unchecked")
        SortedLinkedListWithHeader<T> auxi = (SortedLinkedListWithHeader<T>) other;
		
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
		if(root == null) return null;
		return root.getData();
	}


	@Override
	public T getMax() {
		if(root == null) return null;
		return last.getData();
	}

	@Override
	public Iterator<T> iterator() {
		return new SortedLinkedListIterator();
	}

	//END ========================================================	FIN CLASE SORTEDLINKEDLIST

	private class SortedLinkedListIterator implements Iterator<T> {
		private Node lastReturned = null;
		private Node current = root;
		private Node prev = null; // Nuevo campo para mantener referencia al nodo anterior

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastReturned = current;
			prev = (lastReturned == root) ? null : prev == null ? root : prev.next;
			current = current.next;
			return lastReturned.data;
		}

		@Override
		public void remove() {
			if (lastReturned == null) {
				throw new IllegalStateException("next() must be called before calling remove().");
			}
			if (lastReturned == root) {
				root = root.next;
				// Si luego de actualizar root, lista queda vacía, también actualizamos last
				if (root == null) {
					last = null;
				}
			} else {
				prev.next = lastReturned.next;
				if (lastReturned.next == null) { // Si estamos eliminando el último elemento
					last = prev;
				}
			}
			lastReturned = null; // Reset lastReturned after removal
			size--;
		}
	}




	private final class Node {
		private T data;
		private Node next;

		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		public T getData(){
			return this.data;
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
		SortedLinkedListWithHeader<String> list = new SortedLinkedListWithHeader<>();

		// Prueba de inserción
		System.out.println("Insertando elementos...");
		list.insert("banana");
		list.insert("apple");
		list.insert("cherry");
		list.dump();

		// Prueba de eliminación
		System.out.println("Eliminando 'apple'...");
		list.remove("apple");
		list.dump();

		// Prueba de búsqueda
		System.out.println("Buscando 'banana'...");
		System.out.println(list.find("banana") ? "Elemento encontrado." : "Elemento no encontrado.");

		// Prueba de obtener mínimo y máximo
		System.out.println("Elemento mínimo: " + list.getMin());
		System.out.println("Elemento máximo: " + list.getMax());

		// Prueba del tamaño de la lista
		System.out.println("Tamaño de la lista: " + list.size());

		// Prueba de la lista vacía
		System.out.println("La lista está vacía: " + list.isEmpty());

		// Prueba de iterador
		System.out.println("Iterando sobre la lista...");
		for (String s : list) {
			System.out.println(s);
		}

		// Prueba de la funcionalidad del iterador remove
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String data = it.next();
			if (data.equals("cherry")) {
				it.remove();
			}
		}
		System.out.println("Lista después de eliminar 'cherry' con el iterador:");
		list.dump();

		// Prueba de igualdad
		SortedLinkedListWithHeader<String> anotherList = new SortedLinkedListWithHeader<>();
		anotherList.insert("banana");
		System.out.println("Listas iguales: " + list.equals(anotherList));
	}



}
