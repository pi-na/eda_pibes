package ej2;
import org.junit.Test;
import static org.junit.Assert.*;

public class IndexWithDuplicatesTest {
    @Test
    public void testCase1() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[]{1, 3, 5, 7});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[]{2, 4, 6, 8});
        index1.merge(index2);
        //Metodo estatico (pq puse junit4 en el pom), hago import static o no anda!!
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, index1.getIndexedData());
    }


    @Test
    public void testCase2() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 1, 3, 5, 7});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 4, 6, 8});
        index1.merge(index2);
        assertArrayEquals(new int[] {1, 1, 2, 3, 4, 4, 5, 6, 7, 8}, index1.getIndexedData());
    }

    @Test
    public void testCase3() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 3, 5});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8, 10});
        index1.merge(index2);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 8, 10}, index1.getIndexedData());
    }

    @Test
    public void testCase4() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {10, 20, 30});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {15, 25, 35});
        index1.merge(index2);
        assertArrayEquals(new int[] {10, 15, 20, 25, 30, 35}, index1.getIndexedData());
    }

    @Test
    public void testCase5() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 1, 1, 1});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 2, 2, 2});
        index1.merge(index2);
        assertArrayEquals(new int[] {1, 1, 1, 1, 2, 2, 2, 2}, index1.getIndexedData());
    }
}