package planner.commons;

import java.util.Objects;

public class InventroyItem {
    private String item;
    private int quantity;
    private String location;

    public InventroyItem() {
    }

    public InventroyItem(String item, int quantity, String location) {
        this.item = item;
        this.quantity = quantity;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventroyItem that = (InventroyItem) o;
        return quantity == that.quantity && Objects.equals(item, that.item) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, quantity, location);
    }

    @Override
    public String toString() {
        return "InventroyItem{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                '}';
    }
}
