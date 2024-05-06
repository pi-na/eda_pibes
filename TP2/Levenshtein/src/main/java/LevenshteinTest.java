import org.junit.Assert;
import org.junit.Test;

public class LevenshteinTest {

    @Test
    public void testDistance1() {
        Levenshtein l = new Levenshtein("kitten".toCharArray(), "sitting".toCharArray());
        Assert.assertEquals(3, l.distance());
    }

    @Test
    public void testDistance2() {
        Levenshtein l = new Levenshtein("rosettacode".toCharArray(), "raisethysword".toCharArray());
        Assert.assertEquals(8, l.distance());
    }

    @Test
    public void testDistance3() {
        Levenshtein l = new Levenshtein("apple".toCharArray(), "apple".toCharArray());
        Assert.assertEquals(0, l.distance());
    }

    @Test
    public void testDistance4() {
        Levenshtein l = new Levenshtein("apple".toCharArray(), "".toCharArray());
        Assert.assertEquals(5, l.distance());
    }

    @Test
    public void testDistance5() {
        Levenshtein l = new Levenshtein("".toCharArray(), "apple".toCharArray());
        Assert.assertEquals(5, l.distance());
    }

    @Test
    public void testDistance6() {
        Levenshtein l = new Levenshtein("apple".toCharArray(), "aple".toCharArray());
        Assert.assertEquals(1, l.distance());
    }

    @Test
    public void testDistance7() {
        Levenshtein l = new Levenshtein("apple".toCharArray(), "appple".toCharArray());
        Assert.assertEquals(1, l.distance());
    }

    @Test
    public void testDistance8() {
        Levenshtein l = new Levenshtein("apple".toCharArray(), "bpple".toCharArray());
        Assert.assertEquals(1, l.distance());
    }

    @Test
    public void testDistance9() {
        Levenshtein l = new Levenshtein("apple".toCharArray(), "bpple".toCharArray());
        Assert.assertEquals(1, l.distance());
    }

    @Test
    public void testDistance10() {
        Levenshtein l = new Levenshtein("apple".toCharArray(), "bpple".toCharArray());
        Assert.assertEquals(1, l.distance());
    }

}