import org.junit.jupiter.api.*;

public class TimerTest {
    @BeforeAll
    static void initAll() {
        System.out.println("Inicio de los Basic Tests");
    }

    private static int testCounter = 0;
    private Timer timer;
    private final int initTime = 100;

    @BeforeEach
    void setup() {
        timer = new Timer(initTime);
        ++testCounter;
        System.out.println("Inicio del Basic Test " + testCounter);
    }

    @Test
    void TestZero() {
        timer.stop(initTime);
        Assertions.assertEquals("(0ms) 0 días 0 horas 0 mins 0,0 s", timer.toString() );
    }

    @Test
    void TestMillis() {
        timer.stop(initTime + 100);
        Assertions.assertEquals("(100ms) 0 días 0 horas 0 mins 0,100 s", timer.toString() );
    }

    @Test
    void TestSeconds() {
        timer.stop(initTime + 11*1000);
        Assertions.assertEquals("(11000ms) 0 días 0 horas 0 mins 11,000 s", timer.toString() );
    }

    @Test
    void TestMinutes() {
        timer.stop(initTime + 25*60*1000);
        Assertions.assertEquals("(1500000 ms) 0 dÃ­as 0 horas 25 min 0,000 seg", timer.toString() );
    }

    @Test
    void TestHours() {
        timer.stop(initTime + 13*60*60*1000);
        Assertions.assertEquals("(46800000 ms) 0 dÃ­as 13 horas 0 min 0,000 seg", timer.toString() );
    }

    @Test
    void TestDays() {
        timer.stop(initTime + 3*24*60*60*1000);
        Assertions.assertEquals("(259200000 ms) 3 dÃ­as 0 horas 0 min 0,000 seg", timer.toString() );
    }

    @Test
    void TestComplete() {
        timer.stop(initTime + 3*24*60*60*1000 + 13*60*60*1000 + 25*60*1000 + 11*1000 + 123 );
        Assertions.assertEquals("(307511123 ms) 3 dÃ­as 13 horas 25 min 11,123 seg", timer.toString() );
    }

    @Test
    void TestNoParams() {
        Timer timer2 = new Timer();
        timer2.stop(); // para lograr 100% de coverage. No es un buen test!
        Assertions.assertTrue( timer2.toString().contains("(0 ms) 0 dÃ­as 0 horas 0 min 0,") );
    }

    @AfterEach
    void tearDown() {
        System.out.println("Fin del Basic test " + testCounter);
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Fin de los Basic Tests");
    }
}
