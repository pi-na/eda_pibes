import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IndexWithDuplicatesTest {
    private IndexWithDuplicates<Integer> index;

    @Before
    public void setUp() {
        index = new IndexWithDuplicates<>();
    }

    @Test
    public void testInitialize() {
        index.initialize(new Integer[]{1, 2, 3});
        assertEquals(1, index.occurrences(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializeWithNull() {
        index.initialize(null);
    }

    @Test
    public void testSearch() {
        index.initialize(new Integer[]{1, 2, 3});
        assertTrue(index.search(2));
        Assert.assertFalse(index.search(4));
    }

    @Test(expected = RuntimeException.class)
    public void testSearchInEmptyIndex() {
        index.search(1);
    }

    @Test
    public void testInsert() {
        index.initialize(new Integer[]{1, 2, 3});
        index.insert(4);
        assertTrue(index.search(4));
    }

    @Test
    public void testDelete() {
        index.initialize(new Integer[]{1, 2, 3});
        index.delete(2);
        Assert.assertFalse(index.search(2));
    }

    @Test
    public void testOccurrences() {
        index.initialize(new Integer[]{1, 2, 2, 3});
        assertEquals(2, index.occurrences(2));
    }

    @Test
    public void testRange() {
        index.initialize(new Integer[]{1, 2, 3, 4, 5});
        Assert.assertArrayEquals(new Integer[]{2, 3, 4}, index.range(2, 4, true, true));
    }

    @Test
    public void testGetMax() {
        index.initialize(new Integer[]{1, 2, 3});
        assertEquals(Integer.valueOf(3), index.getMax());
    }

    @Test(expected = RuntimeException.class)
    public void testGetMaxInEmptyIndex() {
        index.getMax();
    }

    @Test
    public void testGetMin() {
        index.initialize(new Integer[]{1, 2, 3});
        assertEquals(Integer.valueOf(1), index.getMin());
    }

    @Test(expected = RuntimeException.class)
    public void testGetMinInEmptyIndex() {
        index.getMin();
    }
}