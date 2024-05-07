import org.junit.Test;
import static org.junit.Assert.*;

public class SortTest {

    @Test
    public void testQuickSortHelper1() {
        int[] unsorted = {5, 3, 7, 6, 2, 1};
        int[] expected = {1, 2, 3, 5, 6, 7};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper2() {
        int[] unsorted = {1, 2, 3, 4, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper3() {
        int[] unsorted = {6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper4() {
        int[] unsorted = {1};
        int[] expected = {1};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper5() {
        int[] unsorted = {2, 1};
        int[] expected = {1, 2};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper6() {
        int[] unsorted = {1, 2};
        int[] expected = {1, 2};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper7() {
        // Este test falla con la implementacion actual!
        int[] unsorted = {1, 1, 1, 1};
        int[] expected = {1, 1, 1, 1};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper8() {
        int[] unsorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper9() {
        int[] unsorted = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }

    @Test
    public void testQuickSortHelper10() {
        int[] unsorted = {1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Sort.quickSortHelper(unsorted, 0, unsorted.length - 1);
        assertArrayEquals(expected, unsorted);
    }
}