package planner.commons;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Statement {
    private int id;
    private boolean isIncome;
    private int amount;
    private String category;
    private GregorianCalendar date;

    public Statement(int id, boolean isIncome, int amount, String category, GregorianCalendar date) {
        this.id = id;
        this.isIncome = isIncome;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        Statement statement = (Statement) o;
        return isIncome == statement.isIncome && amount == statement.amount && Objects.equals(category, statement.category) && Objects.equals(date, statement.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isIncome, amount, category, date);
    }

    @Override
    public String toString() {
        return "Statement{" +
                "isIncome=" + isIncome +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", date=" + date +
                '}';
    }
}
