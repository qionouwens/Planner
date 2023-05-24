package planner.commons;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Todo {
    private int id;
    private String description;
    private GregorianCalendar date;

    public Todo() {
    }

    public Todo(int id, String description, GregorianCalendar date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id && Objects.equals(description, todo.description) && Objects.equals(date, todo.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, date);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
