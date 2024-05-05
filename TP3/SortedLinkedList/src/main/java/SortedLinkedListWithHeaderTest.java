import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SortedLinkedListWithHeaderTest {
    private SortedLinkedListWithHeader<String> list;

    @Before
    public void setUp() {
        list = new SortedLinkedListWithHeader<>();
    }

    @Test
    public void testInsert() {
        assertTrue(list.insert("banana"));
        assertTrue(list.insert("apple"));
        assertFalse(list.insert("apple")); // testing duplicate insertion
    }

    @Test
    public void testRemove() {
        list.insert("banana");
        assertTrue(list.remove("banana"));
        assertFalse(list.remove("banana")); // testing removal of non-existing element
    }

    @Test
    public void testFind() {
        list.insert("banana");
        assertTrue(list.find("banana"));
        assertFalse(list.find("apple")); // testing find on non-existing element
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.insert("banana");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.insert("banana");
        assertEquals(1, list.size());
    }

    @Test
    public void testGetMin() {
        assertNull(list.getMin());
        list.insert("banana");
        assertEquals("banana", list.getMin());
    }

    @Test
    public void testGetMax() {
        assertNull(list.getMax());
        list.insert("banana");
        assertEquals("banana", list.getMax());
    }

    @Test
    public void testDump() {
        list.insert("banana");
        list.insert("apple");
        list.dump(); // this is a void method, just checking if it runs without exceptions
    }

    @Test
    public void testIterator() {
        list.insert("banana");
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("banana", it.next());
    }

    @Test
    public void testIteratorRemove() {
        list.insert("banana");
        Iterator<String> it = list.iterator();
        it.next();
        it.remove();
        assertFalse(list.find("banana"));
    }
}