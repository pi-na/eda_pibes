package generics_implementation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IndexWithDuplicatesParamTest {
    private IndexWithDuplicatesParam<Integer> index;

    @Before
    public void setUp() {
        index = new IndexWithDuplicatesParam<>(Integer.class);
    }

    @Test
    public void testInsert() {
        index.insert(5);
        assertTrue(index.search(5));
    }

    @Test
    public void testSearch() {
        index.insert(10);
        assertTrue(index.search(10));
        assertFalse(index.search(5));
    }

    @Test
    public void testDelete() {
        index.insert(15);
        index.delete(15);
        assertFalse(index.search(15));
    }

    @Test
    public void testOccurrences() {
        index.insert(20);
        index.insert(20);
        assertEquals(2, index.occurrences(20));
    }

    @Test
    public void testRange() {
        index.insert(25);
        index.insert(30);
        index.insert(35);
        Integer[] expected = {30, 35};
        assertArrayEquals(expected, index.range(30, 40, true, true));
    }

    @Test
    public void testGetMax() {
        index.insert(40);
        index.insert(45);
        assertEquals(Integer.valueOf(45), index.getMax());
    }

    @Test
    public void testGetMin() {
        index.insert(50);
        index.insert(55);
        assertEquals(Integer.valueOf(50), index.getMin());
    }
}