import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClosedHashingTest {
    private IndexParametricService<Integer, String> hash;

    @BeforeEach
    public void setup() {
        hash = new ClosedHashing<>(f->f);
    }

    @Test
    public void test1() {
        hash.insertOrUpdate(1, "hola1");
        hash.insertOrUpdate(2, "hola2");
        hash.insertOrUpdate(3, "hola3");
        hash.insertOrUpdate(4, "hola4");
        hash.insertOrUpdate(5, "hola5");
        hash.remove(1);
        hash.remove(15);
        hash.dump();

    }

    @Test
    public void test2() {
        hash.insertOrUpdate(3, "dick");
        hash.insertOrUpdate(23, "joe");
        hash.insertOrUpdate(4, "sue");
        hash.insertOrUpdate(15, "meg");
        hash.remove(23);
        hash.remove(15);
        hash.insertOrUpdate(4, "sue2");
        hash.insertOrUpdate(43,"paul");
        hash.dump();
    }

    @Test
    public void test3() {
        hash.insertOrUpdate(1, "hola1");
        hash.insertOrUpdate(2, "hola2");
        hash.remove(1);
        hash.insertOrUpdate(3, "hola3");
        hash.remove(2);
        hash.insertOrUpdate(4, "hola4");
        hash.remove(3);
        hash.insertOrUpdate(5, "hola5");
        hash.remove(4);
        hash.insertOrUpdate(6, "hola6");
        hash.remove(5);
        hash.dump();
    }

    @Test
    public void test4() {
        hash.insertOrUpdate(5,"E");
        hash.insertOrUpdate(15,"E");
        hash.insertOrUpdate(25,"E");
        hash.insertOrUpdate(35,"E");
        hash.insertOrUpdate(45,"E");
        hash.insertOrUpdate(55,"E");
        hash.insertOrUpdate(65,"E");
        hash.insertOrUpdate(75,"E");
        hash.insertOrUpdate(85,"E");
        hash.insertOrUpdate(95,"E");
        hash.insertOrUpdate(105,"E");
        System.out.println(hash.size());
        hash.dump();
    }
    
    @Test
    public void test5() {
        hash.insertOrUpdate(0,"E");
        hash.insertOrUpdate(10,"E");
        hash.insertOrUpdate(20,"E");
        hash.insertOrUpdate(30,"E");
        hash.insertOrUpdate(40,"E");
        hash.insertOrUpdate(50,"E");
        hash.insertOrUpdate(60,"E");
        hash.remove(10);
        hash.remove(20);
        hash.remove(30);
        hash.insertOrUpdate(7,"E");
        hash.insertOrUpdate(8,"E");
        hash.insertOrUpdate(9,"E");
        hash.dump();
    }

    @Test
public void test6() {
    hash.insertOrUpdate(1, "test6");
    assertEquals("test6", hash.find(1));
    hash.dump();
}

@Test
public void test7() {
    hash.insertOrUpdate(2, "test7");
    assertTrue(hash.remove(2));
    assertNull(hash.find(2));
    hash.dump();
}

@Test
public void test8() {
    hash.insertOrUpdate(3, "test8");
    hash.insertOrUpdate(3, "test8_updated");
    assertEquals("test8_updated", hash.find(3));
    hash.dump();
}

@Test
public void test9() {
    assertFalse(hash.remove(4));
    hash.dump();
}

@Test
public void test10() {
    hash.insertOrUpdate(5, "test10");
    assertEquals(1, hash.size());
    hash.dump();
}

@Test
public void test11() {
    hash.insertOrUpdate(6, "test11");
    hash.insertOrUpdate(7, "test11");
    hash.insertOrUpdate(8, "test11");
    assertEquals(3, hash.size());
    hash.dump();
}

@Test
public void test12() {
    hash.insertOrUpdate(9, "test12");
    hash.remove(9);
    assertEquals(0, hash.size());
    hash.dump();
}

@Test
public void test13() {
    hash.insertOrUpdate(10, "test13");
    hash.insertOrUpdate(11, "test13");
    hash.remove(10);
    assertEquals(1, hash.size());
    hash.dump();
}

@Test
public void test14() {
    hash.insertOrUpdate(12, "test14");
    hash.insertOrUpdate(13, "test14");
    hash.remove(12);
    hash.remove(13);
    assertEquals(0, hash.size());
    hash.dump();
}

@Test
public void test15() {
    hash.insertOrUpdate(14, "test15");
    assertEquals("test15", hash.find(14));
    hash.dump();
}

@Test
public void test16() {
    hash.insertOrUpdate(15, "test16");
    assertTrue(hash.remove(15));
    assertNull(hash.find(15));
    hash.dump();
}

@Test
public void test17() {
    hash.insertOrUpdate(16, "test17");
    hash.insertOrUpdate(16, "test17_updated");
    assertEquals("test17_updated", hash.find(16));
    hash.dump();
}

@Test
public void test18() {
    assertFalse(hash.remove(17));
    hash.dump();
}

@Test
public void test19() {
    hash.insertOrUpdate(18, "test19");
    assertEquals(1, hash.size());
    hash.dump();
}

@Test
public void test20() {
    hash.insertOrUpdate(19, "test20");
    hash.insertOrUpdate(20, "test20");
    hash.insertOrUpdate(21, "test20");
    assertEquals(3, hash.size());
    hash.dump();
}
}
