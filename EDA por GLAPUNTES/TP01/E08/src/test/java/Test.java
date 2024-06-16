import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.ThrowingSupplier;

public class Test {
    @org.junit.jupiter.api.Test
    void test1() {
        TimerJoda t = new TimerJoda(10);
        t.stop(62+10);
        Assertions.assertEquals("(62 ms) 0 Days 00 Hours 00 Minutes 0.06 Seconds", t.toString());
    }
    @org.junit.jupiter.api.Test
    void test2() {
        TimerJoda t = new TimerJoda(1);
        Assertions.assertThrows(RuntimeException.class, t::getStop);
        t.stop(16+10);
        Assertions.assertDoesNotThrow((ThrowingSupplier<RuntimeException>) RuntimeException::new);
    }

}
