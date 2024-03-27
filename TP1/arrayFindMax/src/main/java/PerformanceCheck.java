
public class PerformanceCheck {
    public static void execute(int arrayLength) {
        int[] arrayA = new int[arrayLength];
        int rtaA;
        int rtaB;

        // generate array
        for (int rec = arrayLength; rec > 0; rec--)
            arrayA[arrayLength - rec] = rec;

        int[] arrayB = arrayA;

        Timer timerA = new Timer();
        rtaA = AlgoA.max(arrayA);
        timerA.stop();

        Timer timerB = new Timer();
        rtaB = AlgoB.max(arrayB);
        timerB.stop();

        System.out.println(String.format("max element found by Algo A %d. Delay %d (ms)", rtaA, timerA.getElapsedTime()));
        System.out.println(String.format("max element found by Algo B %d. Delay %d (ms)", rtaB, timerB.getElapsedTime()));
    }
}
