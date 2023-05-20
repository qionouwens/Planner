package planner.commons;

import java.util.GregorianCalendar;
import java.util.Objects;

public class CleaningTask {
    private String name;
    private int frequency;
    private GregorianCalendar last_done;

    public CleaningTask(String name, int frequency, GregorianCalendar last_done) {
        this.name = name;
        this.frequency = frequency;
        this.last_done = last_done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public GregorianCalendar getLast_done() {
        return last_done;
    }

    public void setLast_done(GregorianCalendar last_done) {
        this.last_done = last_done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleaningTask that = (CleaningTask) o;
        return frequency == that.frequency && Objects.equals(name, that.name) && Objects.equals(last_done, that.last_done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frequency, last_done);
    }

    @Override
    public String toString() {
        return "CleaningTask{" +
                "name='" + name + '\'' +
                ", frequency=" + frequency +
                ", last_done=" + last_done +
                '}';
    }
}
