import ej1.Lista;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListaTest {

    @Test
    public void TestConstructor() {
        Lista l = new Lista(10);
        assertNotNull("La lista no debería ser nula", l);
        l.dump();
    }

    @Test
    public void TestRandomSplitListas_CasosPrincipales() {
        // Caso A
        Lista lA = new Lista(10);
        Lista[] casoA = lA.randomSplitListas(4);
        assertEquals("Debería haber 4 listas", 4, casoA.length);

        // Caso B
        Lista lB = new Lista(5, 7, true);
        Lista[] casoB = lB.randomSplitListas(6);
        assertEquals("Debería haber 6 listas", 6, casoB.length);

        // Caso C
        Lista lC = new Lista(5, 7, false);
        Lista[] casoC = lC.randomSplitListas(6);
        assertEquals("Debería haber 6 listas", 6, casoC.length);
    }

    @Test
    public void TestRandomSplitVacio() {
        Lista l = new Lista();
        Lista[] resultado = l.randomSplitListas(3);
        assertEquals("Deberían ser 3 listas", 3, resultado.length);
    }

    @Test
    public void TestRandomSplitUnElemento() {
        Lista l = new Lista(1);
        Lista[] resultado = l.randomSplitListas(1);
        assertEquals("Debería ser 1 lista", 1, resultado.length);
    }

    @Test
    public void TestRandomSplitMasListasQueElementos() {
        Lista l = new Lista(2);
        Lista[] resultado = l.randomSplitListas(5);
        assertEquals("Deberían ser 5 listas", 5, resultado.length);
    }

    @Test
    public void TestRandomSplitListaNull() {
        Lista l = null;
        try {
            Lista[] resultado = l.randomSplitListas(3);
            fail("Debería lanzar NullPointerException");
        } catch (NullPointerException e) {
            assertTrue("Excepción capturada correctamente", true);
        }
    }
}
