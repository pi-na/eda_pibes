import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IndexParametricImplTest {
    @Test
    void rangeTest() {
        IndexParametricImpl<Integer> index = new IndexParametricImpl<>();
        index.initialize(new Integer[]{10,20,20,20,20,30,40,50,60,70,80,90,100,100});
        Assertions.assertArrayEquals(new Integer[]{30,40,50,60}, index.range(30,60,true, true));
        Assertions.assertArrayEquals(new Integer[]{40,50}, index.range(30,60,false, false));
        Assertions.assertArrayEquals(new Integer[]{40,50}, index.range(33,60,true, false));
        Assertions.assertArrayEquals(new Integer[]{10,20,20,20,20,30,40,50,60}, index.range(0,60,false, true));
        Assertions.assertArrayEquals(new Integer[]{}, index.range(1100,60,false, false));
        Assertions.assertArrayEquals(new Integer[]{20,20,20,20,30,40,50,60,70,80,90}, index.range(10,100,false, false));
        Assertions.assertArrayEquals(new Integer[]{10,20,20,20,20,30,40,50,60,70,80,90,100,100}, index.range(10,100,true, true));
    }
}