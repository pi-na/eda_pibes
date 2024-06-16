import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ej5_IslandsTest {

  @Test
  void singleIsland() {
    boolean[][] map = new boolean[][] {
        new boolean[] { true, true, true, true, false },
        new boolean[] { true, true, false, true, false },
        new boolean[] { true, true, false, false, false },
        new boolean[] { false, false, false, false, false }
    };
    int islands = Ej5_Islands.countIslands(map);
    assertEquals(1, islands);
  }

  @Test
  void manyIslands() {
    boolean[][] map = new boolean[][] {
        new boolean[] { true, true, false, false, false },
        new boolean[] { true, true, false, false, false },
        new boolean[] { false, false, true, false, false },
        new boolean[] { false, false, false, true, true }
    };
    int islands = Ej5_Islands.countIslands(map);
    assertEquals(3, islands);
  }

  @Test
  void biggestSingleIsland() {
    boolean[][] map = new boolean[][] {
        new boolean[] { true, true, true, true, false },
        new boolean[] { true, true, false, true, false },
        new boolean[] { true, true, false, false, false },
        new boolean[] { false, false, false, false, false }
    };
    int biggest = Ej5_Islands.biggestIsland(map);
    assertEquals(9, biggest);
  }

  @Test
  void biggestOfManyIsland() {
    boolean[][] map = new boolean[][] {
        new boolean[] { true, true, false, false, false },
        new boolean[] { true, true, false, false, false },
        new boolean[] { false, false, true, false, false },
        new boolean[] { false, false, false, true, true }
    };
    int biggest = Ej5_Islands.biggestIsland(map);
    assertEquals(4, biggest);
  }
}
