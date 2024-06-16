import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList <T extends Comparable<? super T>> implements SortedListService<T> {
    protected Node firstElement;
    protected Node lastElement;
    protected int size;

    @Override
    public void dump() {
        StringBuilder string = new StringBuilder();
        for (Node rec = firstElement; rec != null; rec = rec.next) {
            string.append(rec.value);
            if(rec.next != null) {
                string.append(" -> ");
            }
        }
        System.out.println(string.toString());
    }

    public void reverseDump() {
        StringBuilder string = new StringBuilder();
        for (Node rec = lastElement; rec != null; rec = rec.prev) {
            string.append(rec.value);
            if (rec.prev != null) {
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
            firstElement = new Node(element, null, null);
            lastElement = firstElement;
            size++;
            return;
        }

        Node prev = firstElement; Node rec = firstElement;

        while (rec != null && rec.value.compareTo(element) < 0) {
            // go on. After this cycle element should go between prev and rec.
            prev = rec;
            rec = rec.next;
        }

        // repeated?
        if (rec != null && rec.value.compareTo(element) == 0) {
            System.err.printf("Insertion failed. %s repeated%n", element);
            return;
        }

        Node newNode = new Node(element, rec, prev);

        // does the first element change?
        if (prev == rec) {
            firstElement = newNode;
            newNode.prev = null;
        } else {
            prev.next = newNode;
            if (rec != null) {
                rec.prev = newNode;
            }
        }

        if (lastElement.value.compareTo(element) < 0) {
            lastElement.next = newNode;
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
                if (rec.value.compareTo(firstElement.value)==0) {
                    firstElement = rec.next;
                    firstElement.prev = null;
                }
                else if (rec.value.compareTo(lastElement.value)==0) {
                    lastElement = prev;
                    lastElement.next = null;
                }
                else {
                    // We need to update the previous value of the next one.
                    rec.next.prev = prev;
                    prev.next = rec.next;
                }
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
            System.out.println("There is no minimum element.");
            return null;
        }
        return firstElement.value;
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            System.out.println("There is no maximum element.");
            return null;
        }
        return lastElement.value;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            Node next = firstElement;
            Node current = firstElement;
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
                current = next;
                next = next.next;
                canRemove = true;
                return current.value;
            }

            @Override
            public void remove() {
                if(!canRemove) {
                    throw new IllegalStateException("next() method not called.");
                }
                if(current == firstElement) {
                    firstElement = current.next;
                    firstElement.prev = null;
                }
                if(current == lastElement) {
                    lastElement = current.prev;
                    lastElement.next = null;
                }
                if(current.prev != null) {
                    current.prev.next = next;
                }
                if(next != null) {
                    next.prev = current.prev;
                }
                canRemove = false;
                size--;
            }
        };

    }


    protected final class Node {
        T value;
        Node next;
        Node prev;

        // do not accept nulls in the data.
        Node(T value, Node next, Node prev) {
            if (value == null)
                throw new RuntimeException("Null is not accepted for data");
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

    }


}
