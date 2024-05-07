package int_implementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IndexWithDuplicatesTest {
    private IndexWithDuplicates index;

    @Before
    public void setUp() {
        index = new IndexWithDuplicates();
    }

    @Test
    public void testInsert() {
        index.insert(5);
        Assert.assertTrue(index.search(5));
    }

    @Test
    public void testSearch() {
        index.insert(10);
        Assert.assertTrue(index.search(10));
        Assert.assertFalse(index.search(5));
    }

    @Test
    public void testDelete() {
        index.insert(15);
        index.delete(15);
        Assert.assertFalse(index.search(15));
    }

    @Test
    public void testOccurrences() {
        index.insert(20);
        index.insert(20);
        Assert.assertEquals(2, index.occurrences(20));
    }

    @Test
    public void testRange() {
        index.insert(25);
        index.insert(30);
        index.insert(35);
        int[] expected = {30, 35};
        Assert.assertArrayEquals(expected, index.range(30, 40, true, true));
    }

    @Test
    public void testGetMax() {
        index.insert(40);
        index.insert(45);
        Assert.assertEquals(45, index.getMax());
    }

    @Test
    public void testGetMin() {
        index.insert(50);
        index.insert(55);
        Assert.assertEquals(50, index.getMin());
    }
}