import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

// lista simplemente encadenada, no acepta repetidos (false e ignora) ni nulls (exception)
public class SortedLinkedList<T extends Comparable<? super T>> implements SortedListService<T>{
	private Node root;
	private int size;
	private Node last;

	//	delega en Node
	@Override
	public boolean insert(T data) {
		if (data == null)
			throw new IllegalArgumentException("data cannot be null");
		
		if (root == null) {
			root = new Node(data, null);
			return true;
		}
		
		boolean[] rta= new boolean[1];
 		root = root.insert(data,  rta);
		if(rta[0]) size++;
		return rta[0];
	}

	@Override
	public boolean find(T data) {
		return getPos(data) != -1;
	}

	@Override
	public boolean remove(T data) {
		if (data == null)
			throw new IllegalArgumentException("data cannot be null");

		Node prev = null;
		Node current = root;

		while( current != null && current.data.compareTo(data) <0) {
			// avanzo
			prev = current;
			current = current.next;
		}

		if(current != null && current.data.compareTo(data) == 0) {
			if(current == root)
				root = current.next;
			else
				prev.next = current.next;
			if(current == last)
				last = prev;
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		return size;
	}
	
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
		if (!(other instanceof SortedLinkedList))
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
			if (current.data.compareTo(data) < 0) //ordenada
				return -1;

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
		if(root==null)
			throw new NoSuchElementException();
		return root.data;
	}

	@Override
	public T getMax() {
		if(root==null)
			throw new NoSuchElementException();
		return last.data;
	}

	@Override
	public Iterator<T> iterator() {
		return new SortedLinkedListIterator();
	}

	private final class Node {
		private final T data;
		private Node next;
		
		private Node(T data) {
			this.data= data;
		}
		
		private Node(T data, Node next) {
			this.data= data;
			this.next= next;
		}
		
		private Node insert(T data, boolean[] rta) {
			
			if (this.data.compareTo(data) == 0) {
				System.err.printf("Insertion failed %s%n", data);
				rta[0]= false;
				return this;
			}

			if(this.data.compareTo(data) < 0) {
				// soy el ultimo?
				if (next==null) {
					rta[0]= true;
					next   = new Node(data, null);
					return this;
				}
				// avanzo
				next   = next.insert(data, rta);
				return this;
			}
			
			// estoy en parado en el lugar a insertar
			rta[0]= true;
			return new Node(data, this);
		}
	}

	private class SortedLinkedListIterator implements Iterator<T> {
		private Node prev = null;
		private Node current = root;

		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			T toReturn = current.data;
			prev = current;
			current = current.next;
			return toReturn;
		}

		@Override
		public void remove() {
			if(current == null){
				throw new IllegalStateException("list is empty");
			}
			if(current == root){
				root = current.next;
				return;
			}
			if(prev == null){
				throw new IllegalStateException("must get next element before removing");
			}
			prev.next = current.next;
			current = prev;
			prev = null;
		}
	}

	public static void main(String[] args) {
		SortedLinkedList<Integer> list = new SortedLinkedList<>();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		Iterator<Integer> iter = list.iterator();
		System.out.println(iter.next());
		System.out.println("=======");
		iter.remove();
		for(Integer n : list){
			System.out.println(n);
		}

	}
}
