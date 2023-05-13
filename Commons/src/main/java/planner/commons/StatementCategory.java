package planner.commons;

import java.util.Objects;

public class StatementCategory {
    private int id;
    private String name;
    private int budget;

    public StatementCategory(int id, String name, int budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatementCategory that = (StatementCategory) o;
        return budget == that.budget && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, budget);
    }

    @Override
    public String toString() {
        return "StatementCategory{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }
}
