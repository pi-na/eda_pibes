import org.junit.jupiter.api.Assertions;
public class JUnit {
    @org.junit.jupiter.api.Test
    public void test1(){
        String aux = "SZLLOYDTIRUL";
        Assertions.assertArrayEquals(new String("S436").toCharArray(), Soundex.encode(aux).toCharArray());
    }
}
