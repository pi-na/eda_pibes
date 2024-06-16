import java.time.Duration;
import java.time.Instant;

public class Timer {
    private final Instant start;
    private Instant end;
    private Duration elapsedTime;
    private long minutes, hours, days;
    private double seconds;

    public Timer(long start){
        this.start = Instant.ofEpochMilli(start);
    }

    public Timer(){
        this(System.currentTimeMillis());
    }

    public Instant getEnd() {
        validate();
        return end;
    }

    public void stop(long end){
        if(start.isAfter(Instant.ofEpochMilli(end))){
            throw new RuntimeException("Bad Origin");
        }
        this.end = Instant.ofEpochMilli(end);
        elapsedTime = Duration.between(start,this.end);
        days = elapsedTime.toDays();
        hours = elapsedTime.toHours() - days * 24;
        minutes = elapsedTime.toMinutes() - (elapsedTime.toHours() * 60);
        seconds = elapsedTime.toMillis() / (double) 1000 - (elapsedTime.toMinutes() * 60);

    }
    public void stop(){
        stop(System.currentTimeMillis());
    }

    public long getMinutes() {
        validate();
        return minutes;
    }

    public long getHours() {
        validate();
        return hours;
    }

    public long getDays() {
        validate();
        return days;
    }

    public double getSeconds() {
        validate();
        return seconds;
    }

    public void validate(){
        if(end == null)
            throw new RuntimeException("No stop");
    }

    @Override
    public String toString() {
        validate();
        return String.format("(%d ms) %d Days %02d Hours %02d Minutes %.2f Seconds",
                elapsedTime.toMillis(), days, hours, minutes, seconds);
    }
}
