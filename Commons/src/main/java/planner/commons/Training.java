package planner.commons;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Training {
    private int id;
    private String trainingType;
    private String description;
    private GregorianCalendar calendar;
    private List<TrainingPart> trainingParts;

    public Training() {
    }

    public Training(int id, String trainingType, String description, GregorianCalendar calendar, List<TrainingPart> trainingParts) {
        this.id = id;
        this.trainingType = trainingType;
        this.description = description;
        this.calendar = calendar;
        this.trainingParts = trainingParts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
    }

    public List<TrainingPart> getTrainingParts() {
        return trainingParts;
    }

    public void setTrainingParts(List<TrainingPart> trainingParts) {
        this.trainingParts = trainingParts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return id == training.id && Objects.equals(trainingType, training.trainingType) && Objects.equals(description, training.description) && Objects.equals(calendar, training.calendar) && Objects.equals(trainingParts, training.trainingParts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainingType, description, calendar, trainingParts);
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingType='" + trainingType + '\'' +
                ", description='" + description + '\'' +
                ", calendar=" + calendar +
                ", trainingParts=" + trainingParts +
                '}';
    }
}
