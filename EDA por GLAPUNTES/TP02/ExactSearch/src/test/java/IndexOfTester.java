import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IndexOfTester {
    /**
    @Test
    void ExactSearchTest1() {
        Assertions.assertEquals(2, ExactSearch.indexOf("abracadabra".toCharArray(), "ra".toCharArray()));
        Assertions.assertEquals(0, ExactSearch.indexOf("abracadabra".toCharArray(), "abra".toCharArray()));
        Assertions.assertEquals(-1, ExactSearch.indexOf("abracadabra".toCharArray(), "aba".toCharArray()));
        Assertions.assertEquals(-1, ExactSearch.indexOf("ab".toCharArray(), "aba".toCharArray()));
        Assertions.assertEquals(-1, ExactSearch.indexOf("xa".toCharArray(), "aba".toCharArray()));
        Assertions.assertEquals(7, ExactSearch.indexOf("abracadabras".toCharArray(), "abras".toCharArray()));
    }
    @Test
    void IndexOfTest() {
        Assertions.assertEquals(2, KMP.indexOf("abracadabra".toCharArray(), "ra".toCharArray()));
        Assertions.assertEquals(0, KMP.indexOf("abracadabra".toCharArray(), "abra".toCharArray()));
        Assertions.assertEquals(-1, KMP.indexOf("abracadabra".toCharArray(), "aba".toCharArray()));
        Assertions.assertEquals(-1, KMP.indexOf("ab".toCharArray(), "aba".toCharArray()));
        Assertions.assertEquals(-1, KMP.indexOf("xa".toCharArray(), "aba".toCharArray()));
        Assertions.assertEquals(7, KMP.indexOf("abracadabras".toCharArray(), "abras".toCharArray()));
    }
    */
    @Test
    void findAllTest() {
        /*
        char[] query= "no".toCharArray();
        char[] target= "sino se los digo no se si es nocivo".toCharArray();
        System.out.println(KMP.findAll(query, target));  //debería devolver una instancia de arraylist  con las posiciones  2, 17, 29


        query= "ni".toCharArray();
        target= "sino se los digo no se si es nocivo".toCharArray();
        System.out.println(KMP.findAll(query, target)); //  debería devolver un empty arraylist

        query = "aaa".toCharArray();
        target = "aaabaaaaab".toCharArray();
        System.out.println(KMP.findAll(query, target)); //  0, 4, 5, 6
         */
        List<Integer> res = KMP.findAll("aaabaaaaab".toCharArray(), "aaa".toCharArray());
//        Assertions.assertArrayEquals(new Integer[] {0, 4, 5, 6},res.toArray());
    }

}
