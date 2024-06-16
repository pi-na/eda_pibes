import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ej9_CiudadesYTrenesTest {

  @Test
  void closeTrains() {
    int[][] roads = new int[][] {
        new int[] { 1, 2, 1 },
        new int[] { 2, 3, 2 },
        new int[] { 3, 4, 4 },
        new int[] { 1, 5, 5 }
    };
    int[][] trains = new int[][] {
        new int[] { 3, 5 },
        new int[] { 4, 5 },
        new int[] { 5, 5 }
    };
    int trainsToClose = Ej9_CiudadesYTrenes.trainsToClose(5, roads, trains);
    assertEquals(2, trainsToClose);
  }

  @Test
  void closeTrains2() {
    int[][] roads = new int[][] {
        new int[] { 1, 2, 2 },
        new int[] { 2, 1, 3 },
    };
    int[][] trains = new int[][] {
        new int[] { 2, 1 },
        new int[] { 2, 2 },
        new int[] { 2, 3 }
    };
    int trainsToClose = Ej9_CiudadesYTrenes.trainsToClose(2, roads, trains);
    assertEquals(2, trainsToClose);
  }

  @Test
  void closeTrains3() {
    int[][] roads = new int[][] {
        new int[] { 1, 2, 1 },
        new int[] { 2, 3, 2 },
        new int[] { 3, 4, 4 },
        new int[] { 1, 5, 5 },
        new int[] { 5, 6, 1 }
    };
    int[][] trains = new int[][] {
        new int[] { 3, 5 },
        new int[] { 4, 5 },
        new int[] { 5, 5 },
        new int[] { 6, 6 }
    };
    int trainsToClose = Ej9_CiudadesYTrenes.trainsToClose(6, roads, trains);
    assertEquals(3, trainsToClose);
  }

  @Test
  void closeTrains4() {
    int[][] roads = new int[][] {
        new int[] { 1, 2, 2 },
        new int[] { 2, 3, 3 },
        new int[] { 3, 4, 1 },
    };
    int[][] trains = new int[][] {
        new int[] { 3, 3 },
        new int[] { 4, 5 },
    };
    int trainsToClose = Ej9_CiudadesYTrenes.trainsToClose(4, roads, trains);
    assertEquals(1, trainsToClose);
  }
}
