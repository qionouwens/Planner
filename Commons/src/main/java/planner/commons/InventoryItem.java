package planner.commons;

import java.util.Objects;

public class InventoryItem {
    private int id;
    private String item;
    private int quantity;
    private String location;

    public InventoryItem() {
    }

    public InventoryItem(int id, String item, int quantity, String location) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        InventoryItem that = (InventoryItem) o;
        return id == that.id && quantity == that.quantity && Objects.equals(item, that.item) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, quantity, location);
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                '}';
    }
}
