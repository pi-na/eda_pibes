import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StringSearchTest {

    @Test
    public void testKMPIndexOf() {
        KMP kmp = new KMP();
        char[] target = "abracadabra".toCharArray();
        char[] query = "ra".toCharArray();
        Assert.assertEquals(2, kmp.indexOf(query, target));

        target = "abracadabra".toCharArray();
        query = "abra".toCharArray();
        Assert.assertEquals(0, kmp.indexOf(query, target));

        // Agrega más pruebas aquí...
    }

    @Test
    public void testKMPFindAll() {
        KMP kmp = new KMP();
        char[] target = "aaabaaaaab".toCharArray();
        char[] query = "aaa".toCharArray();
        Assert.assertEquals(List.of(0, 4, 5, 6), kmp.findAll(query, target));

        // Agrega más pruebas aquí...
    }

    @Test
    public void testExactSearchIndexOf() {
        char[] target = "abracadabra".toCharArray();
        char[] query = "ra".toCharArray();
        Assert.assertEquals(2, ExactSearch.indexOf(query, target));

        target = "abracadabra".toCharArray();
        query = "abra".toCharArray();
        Assert.assertEquals(0, ExactSearch.indexOf(query, target));

        // Agrega más pruebas aquí...
    }

    // Agrega más métodos de prueba aquí...

    @Test
    public void testKMPIndexOf2() {
        KMP kmp = new KMP();
        char[] target = "ababcabcabababd".toCharArray();
        char[] query = "ababd".toCharArray();
        Assert.assertEquals(10, kmp.indexOf(query, target));

        target = "abracadabras".toCharArray();
        query = "abras".toCharArray();
        Assert.assertEquals(7, kmp.indexOf(query, target));
    }

    @Test
    public void testKMPFindAll2() {
        KMP kmp = new KMP();
        char[] query = "no".toCharArray();
        char[] target = "sino se los digo no se si es nocivo".toCharArray();
        Assert.assertEquals(List.of(2, 17, 29), kmp.findAll(query, target));

        query = "ni".toCharArray();
        target = "sino se los digo no se si es nocivo".toCharArray();
        Assert.assertTrue(kmp.findAll(query, target).isEmpty());
    }

    @Test
    public void testExactSearchIndexOf2() {
        char[] target = "abracadabra".toCharArray();
        char[] query = "aba".toCharArray();
        Assert.assertEquals(-1, ExactSearch.indexOf(query, target));

        target = "ab".toCharArray();
        query = "aba".toCharArray();
        Assert.assertEquals(-1, ExactSearch.indexOf(query, target));
    }
}