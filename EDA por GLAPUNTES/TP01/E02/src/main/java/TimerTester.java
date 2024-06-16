public class TimerTester {
    public static void main(String[] args) {
        Timer t1= new Timer();
        Timer t2= new Timer(10);

        // bla bla bla
            t1.stop();

        // bla bla bla
            t2.stop(16);

            System.out.println(t1);
            System.out.println(t2);


            t1= new Timer();

            // bla bla bla

            t1.stop(System.currentTimeMillis());

            t2= new Timer(System.currentTimeMillis());

            // bla bla bla

            t2.stop();


    }
}
