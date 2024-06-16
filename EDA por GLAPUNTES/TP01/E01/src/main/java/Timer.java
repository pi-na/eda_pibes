public class Timer {
    private long start, stop;

    public Timer() {
        this(System.currentTimeMillis());
    }

    public Timer(long start) {
        this.start = start;
        stop=-1;
    }

    public void stop() {
        stop=System.currentTimeMillis();
    }

    private void validate() {
        if (stop==-1)
            throw new IllegalStateException();
    }

    @Override
    public String toString() {
        validate();
        long time = stop-start;
        double seconds = time / (double) 1000;
        long days = (int) (seconds / (3600 * 24));
        long hours = (int) ((seconds % (3600 * 24))) / 3600;
        long minutes = (int) (seconds % 3600) / 60;
        double secondsPrint =  seconds % 60;
        return String.format("(%d ms) %d dia %d hs %d min %.2f s",time,days,hours,minutes,secondsPrint);
        //(93623040 ms) 1 día 2 hs 0 min 23,04 s
    }
}
