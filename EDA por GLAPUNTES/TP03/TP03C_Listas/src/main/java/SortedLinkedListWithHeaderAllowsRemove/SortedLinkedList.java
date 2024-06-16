package SortedLinkedListWithHeaderAllowsRemove;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedList<T extends Comparable<? super T>> implements SortedListService<T> {

    protected Node firstElement;
    protected Node lastElement;
    protected int size;

    @Override
    public void dump() {
        StringBuilder string = new StringBuilder();
        for (Node rec= firstElement; rec != null; rec = rec.next) {
            string.append(rec.value);
            if(rec.next != null) {
                string.append(" -> ");
            }
        }
        System.out.println(string.toString());
    }

    @Override
    public boolean isEmpty() {
        return firstElement == null;
    }

    @Override
    public void add(T element) {
        if (isEmpty()) {
            firstElement = new Node(element, null);
            lastElement = firstElement;
            size++;
            return;
        }

        Node prev = firstElement;
        Node rec = firstElement;

        while (rec != null && rec.value.compareTo(element) < 0) {
            // go on
            prev = rec;
            rec = rec.next;
        }

        // repeated?
        if (rec != null && rec.value.compareTo(element) == 0) {
            System.err.println(String.format("Insertion failed. %s repeated", element));
            return;
        }

        Node newNode = new Node(element, rec);

        // does the first element change?
        if (prev == rec) {
            firstElement = newNode;
        } else {
            prev.next = newNode;
        }

        if (lastElement.value.compareTo(element) < 0) {
            lastElement = newNode;
        }
        size++;

    }

    @Override
    public void delete(T element) {
        if (isEmpty()) {
            return;
        }
        for (Node rec = firstElement, prev = firstElement; rec != null; prev = rec, rec = rec.next) {
            if (rec.value.compareTo(element)==0) {
                if (rec.value.compareTo(lastElement.value)==0) {
                    lastElement = prev;
                }
                prev.next = rec.next;
                rec.next=null;
                size--;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            System.out.println("No hay elemento minimo.");
            return null;
        }
        return firstElement.value;
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            System.out.println("No hay elemento maximo.");
            return null;
        }
        return lastElement.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node prev = firstElement;
            Node current = firstElement;
            Node next = firstElement;
            boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return next!=null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                prev = current;
                current = next;
                next = next.next;
                canRemove = true;
                return current.value;
            }

            @Override
            public void remove() {
                if (!canRemove) {
                    throw new IllegalStateException();
                }
                // Unlink the current value.
                prev.next = next;
                if (current.value.compareTo(firstElement.value)==0) {
                    firstElement = current.next;
                }
                if (current.value.compareTo(lastElement.value)==0) {
                    lastElement = prev;
                }
                size--;
                canRemove=false;
            }
        };
    }


    protected final class Node {
        private T value;
        private Node next;

        // do not accept nulls in the data.
        Node(T theValue, Node theNext) {
            if (theValue == null)
                throw new RuntimeException("Null is not accepted for data");
            value = theValue;
            next = theNext;
        }

    }


}