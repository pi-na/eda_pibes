import E00_Correlatives.Correlatives;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Correlativas es el nombre de la clase a implementar y canFinishDegree el nombre del método, sientanse libres de camibarlos.
 * N es la cantidad de materias que hay en total.
 */
public class CorrelativesTest {
    @Test
    void canFinishDegree() {
        int[][] correlativas = new int[][] {
                new int[] { 1, 2 },
                new int[] { 1, 3 },
                new int[] { 3, 2 },
                new int[] { 2, 4 },
        };
        boolean canFinish = Correlatives.canFinishDegree(5, correlativas);
        assertTrue(canFinish);
    }

    @Test
    void cantFinishDegree() {
        int[][] correlativas = new int[][] {
                new int[] { 1, 2 },
                new int[] { 1, 3 },
                new int[] { 3, 2 },
                new int[] { 2, 4 },
                new int[] { 4, 3 },
        };
        boolean canFinish = Correlatives.canFinishDegree(5, correlativas);
        assertFalse(canFinish);
    }
}
