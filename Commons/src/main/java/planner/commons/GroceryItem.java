package planner.commons;

import java.util.Objects;

public class GroceryItem {
    private String item;
    private int quantity;
    private String priority;
    private boolean isGrocery;

    public GroceryItem() {
    }

    public GroceryItem(String item, int quantity, String priority, boolean isGrocery) {
        this.item = item;
        this.quantity = quantity;
        this.priority = priority;
        this.isGrocery = isGrocery;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isGrocery() {
        return isGrocery;
    }

    public void setGrocery(boolean grocery) {
        isGrocery = grocery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroceryItem that = (GroceryItem) o;
        return quantity == that.quantity && isGrocery == that.isGrocery && Objects.equals(item, that.item) && Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, quantity, priority, isGrocery);
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", priority='" + priority + '\'' +
                ", isGrocery=" + isGrocery +
                '}';
    }
}
