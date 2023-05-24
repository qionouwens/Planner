package planner.commons;

import java.util.Objects;

public class GroceryItem {
    private int id;
    private String item;
    private int quantity;
    private String priority;
    private String type;

    public GroceryItem() {
    }

    public GroceryItem(int id, String item, int quantity, String priority, String type) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.priority = priority;
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroceryItem that = (GroceryItem) o;
        return id == that.id && quantity == that.quantity && Objects.equals(item, that.item) && Objects.equals(priority, that.priority) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, quantity, priority, type);
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                ", priority='" + priority + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
