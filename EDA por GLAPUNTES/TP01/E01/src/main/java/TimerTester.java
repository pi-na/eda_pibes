public class TimerTester {
    public static void main(String[] args) {
        Timer timer = new Timer();

        // bla bla bla ….. aca se invocaría el algoritmo cuyo tiempo de ejecución quiere medirse

        timer.stop();
        System.out.println(timer);
    }

//    Salida esperada
//            (93623040 ms) 1 día  2 hs   0 min    23,04 s

}
