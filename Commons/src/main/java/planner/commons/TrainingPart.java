package planner.commons;

import java.util.Objects;

public class TrainingPart {
    private int id;
    private int distance;
    private String time;

    public TrainingPart() {
    }

    public TrainingPart(int id, int distance, String time) {
        this.id = id;
        this.distance = distance;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingPart that = (TrainingPart) o;
        return id == that.id && distance == that.distance && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, time);
    }

    @Override
    public String toString() {
        return "TrainingPart{" +
                "id=" + id +
                ", distance=" + distance +
                ", time='" + time + '\'' +
                '}';
    }
}
