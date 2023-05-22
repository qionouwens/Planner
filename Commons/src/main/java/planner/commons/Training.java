package planner.commons;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Training {
    private String trainingType;
    private String description;
    private GregorianCalendar calendar;
    private List<TrainingPart> trainingParts;

    public Training() {
    }

    public Training(String trainingType, String description, GregorianCalendar calendar, List<TrainingPart> trainingParts) {
        this.trainingType = trainingType;
        this.description = description;
        this.calendar = calendar;
        this.trainingParts = trainingParts;
    }

    public List<TrainingPart> getTrainingParts() {
        return trainingParts;
    }

    public void setTrainingParts(List<TrainingPart> trainingParts) {
        this.trainingParts = trainingParts;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(trainingType, training.trainingType) && Objects.equals(description, training.description) && Objects.equals(calendar, training.calendar) && Objects.equals(trainingParts, training.trainingParts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingType, description, calendar, trainingParts);
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingType='" + trainingType + '\'' +
                ", description='" + description + '\'' +
                ", calendar=" + calendar +
                ", trainingParts=" + trainingParts +
                '}';
    }
}
