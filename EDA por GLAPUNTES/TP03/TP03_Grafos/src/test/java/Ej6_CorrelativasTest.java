import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Ej6_CorrelativasTest {

  @Test
  void canFinishDegree() {
    int[][] correlativas = new int[][] {
        new int[] { 1, 2 },
        new int[] { 1, 3 },
        new int[] { 3, 2 },
        new int[] { 2, 4 },
    };
    boolean canFinish = Ej6_Correlativas.canFinishDegree(5, correlativas);
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
    boolean canFinish = Ej6_Correlativas.canFinishDegree(5, correlativas);
    assertFalse(canFinish);
  }
}
