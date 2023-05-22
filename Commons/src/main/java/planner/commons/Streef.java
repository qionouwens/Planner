package planner.commons;

import java.util.Objects;

public class Streef {
    private String training;
    private String streef;

    public Streef() {
    }

    public Streef(String training, String streef) {
        this.training = training;
        this.streef = streef;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getStreef() {
        return streef;
    }

    public void setStreef(String streef) {
        this.streef = streef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Streef streef1 = (Streef) o;
        return Objects.equals(training, streef1.training) && Objects.equals(streef, streef1.streef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(training, streef);
    }

    @Override
    public String toString() {
        return "Streef{" +
                "training='" + training + '\'' +
                ", streef='" + streef + '\'' +
                '}';
    }
}
