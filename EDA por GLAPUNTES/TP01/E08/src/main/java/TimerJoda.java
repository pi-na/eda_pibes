import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

public class TimerJoda {
    private DateTime start, stop;
    private Period elapsedTime;
    private long minutes, hours, days;
    private double seconds;

    public TimerJoda() {
        this(DateTime.now().getMillis());
    }
    public TimerJoda (long myStart) {
        start = new DateTime(myStart);
        if (start==null || start.isAfter(DateTime.now())) {
            throw new RuntimeException("Bad origin.");
        }
    }
    public void stop() {
        stop(DateTime.now().getMillis());
    }
    public void stop (long myStop) {
        stop = new DateTime(myStop);
        elapsedTime = new Interval(start,stop).toPeriod();
        seconds = elapsedTime.getSeconds() + elapsedTime.getMillis()/(double) 1000;
        minutes = elapsedTime.getMinutes();
        hours = elapsedTime.getHours();
        days = elapsedTime.getDays();
    }

    private void validateInterval() {
        if (stop == null)
            throw new RuntimeException("Stop the timer first.");
    }

    @Override
    public String toString() {
        validateInterval();
        return String.format("(%d ms) %d Days %02d Hours %02d Minutes %.2f Seconds",
                elapsedTime.toStandardDuration().getMillis(),days,hours,minutes,seconds);
    }

    public DateTime getStart() {
        return start;
    }

    public DateTime getStop() {
        validateInterval();
        return stop;
    }

    public Period getElapsedTime() {
        return elapsedTime;
    }

    public long getMinutes() {
        return minutes;
    }

    public long getHours() {
        return hours;
    }

    public long getDays() {
        return days;
    }

    public double getSeconds() {
        return seconds;
    }
}
