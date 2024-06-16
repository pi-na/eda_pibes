import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SoundexTest {

    static String palabra;


    @BeforeAll
    static void initAll() {
        //Si tuviese un parametro que se comparte en todos los tests o la función a testear no es estática y se debe
        //inicializar, podemos aprovechar esta forma de ejecutar código al principio del testeo.
        palabra = "estructura";
    }


    @Test
    void sizeFourTest() {
        // Longitud 4?
        Assertions.assertEquals(4, Soundex.encode(palabra).length());
    }

    @Test
    void noStrangeChars() {
        //Caracteres normales
        Assertions.assertTrue(Soundex.encode(palabra).matches("[A-Z0-9]*"));
    }

    @Test
    void firstChar() {
        //Comienza por letra
        Assertions.assertTrue(Soundex.encode(palabra).substring(0,1).matches("[A-Z]{1}"));
    }

    @Test
    void LastThreeDigits() {
        //Termina en 3 digitos
        Assertions.assertTrue(Soundex.encode(palabra).substring(1).matches("[0-9]{3}"));
    }

    @Test
    void similarityOk() {
        // Funcionamiento correcto de la normalizacion del similarity
        Assertions.assertEquals(Soundex.similarity("face", "feis"), 1);
        Assertions.assertEquals(Soundex.similarity("threshold", "hold"), 0);
        Assertions.assertEquals(Soundex.similarity("phone", "foun"), 0.75);

    }

    @Test
    void encodingOK() {
        //Algunos ejemplos particulares
        Assertions.assertEquals("T624", Soundex.encode("threshold"));
        Assertions.assertEquals("T600", Soundex.encode("thre"));
        Assertions.assertEquals("T000", Soundex.encode("t"));
        Assertions.assertEquals("H000", Soundex.encode("hhhhhh"));
        Assertions.assertEquals("A426", Soundex.encode("algorithm"));
    }




}