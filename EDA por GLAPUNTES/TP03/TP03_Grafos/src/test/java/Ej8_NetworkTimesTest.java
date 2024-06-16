import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ej8_NetworkTimesTest {

  @Test
  void canFinishDegree() {
    int[][] times = new int[][] {
        new int[] { 2, 1, 1 },
        new int[] { 2, 3, 1 },
        new int[] { 3, 4, 1 }
    };

    int delayTime = Ej8_NetworkTimes.delayTime(5, times);
    assertEquals(2, delayTime);
  }
}
