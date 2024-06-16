package TP01.E01_Timer;

public class Timer {
    private final long start;
    private long end;
    boolean stop;

    public Timer() {
        this.start = System.currentTimeMillis();
    }

    public void stop(){
        this.end = System.currentTimeMillis();
        stop=true;
    }

    @Override
    public String toString(){
        if (!stop) {
            throw new IllegalStateException();
        }
        long time = end-start;
        double seconds = (double) time/1000;
        long days = (int) seconds/(3600*24);
        long hours;
        long minutes;
        StringBuilder re = new StringBuilder();
        re.append("(").append(time).append(" ms").append(")");
        return re.toString();
    }

}
