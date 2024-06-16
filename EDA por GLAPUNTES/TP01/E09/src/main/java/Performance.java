public class Performance {

//    public static final int N = 1000000000;
//    public static final int N = 800000000;
    public static final int N=10;
//    public static final int N=100;
//    public static final int N=1000;
//    public static final int N=100000000;
//    public static final int N=200000000;
//    public static final int N=400000000;
//    public static final int N=600000000;

    public static void main(String[] args) {
        // generate array
        int[] myArray = new int[N];

        for (int rec = N; rec > 0; rec--)
            myArray[N - rec] = rec;

        // performance testing
        Timer t1 = new Timer();
        int rta1 = utils.AlgoA.max(myArray);
        t1.stop();

        Timer t2 = new Timer();
        int rta2 = utils.AlgoB.max(myArray);
        t2.stop();

        System.out.println(String.format("max Algo A %d. Delay %d (ms)", rta1, t1.getElapsedTime()));
        System.out.println(String.format("max Algo B %d. Delay %d (ms)", rta2, t2.getElapsedTime()));
    }

}