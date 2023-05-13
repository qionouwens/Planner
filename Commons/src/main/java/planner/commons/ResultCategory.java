package planner.commons;

import java.util.Objects;

public class ResultCategory extends StatementCategory {
    private int month;
    private int amount;
    private boolean isIncome;

    public ResultCategory(int id, String name, int budget, int month, int amount, boolean isIncome) {
        super(id, name, budget);
        this.month = month;
        this.amount = amount;
        this.isIncome = isIncome;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ResultCategory that = (ResultCategory) o;
        return month == that.month && amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), month, amount);
    }

    @Override
    public String toString() {
        return "ResultCategory{" +
                "month=" + month +
                ", amount=" + amount +
                '}';
    }
}
