package planner.database.controller;

import planner.commons.InventoryItem;
import planner.database.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDBController {
    private static Connection connection = new Connect().getConnection();

    public static List<InventoryItem> getAll() {
        String sql = "SELECT inventory_id, description, quantity, location_name " +
                "FROM inventory i " +
                "JOIN location l ON i.location_id = l.location_id ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            List<InventoryItem> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getItem(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addItem(InventoryItem inventoryItem) {
        String sql = "INSERT INTO inventory (description, quantity, location_id)" +
                "   VALUES (?, ?, ?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, inventoryItem.getItem());
            stmt.setInt(2, inventoryItem.getQuantity());
            stmt.setInt(3, getLocationId(inventoryItem.getLocation()));
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteItem(int id) {
        String sql = "DELETE FROM inventory WHERE inventory_id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getLocationId(String location) {
        String sql = "SELECT location_id FROM location " +
                "WHERE location.location_name = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, location);
            return stmt.executeQuery().getInt("location_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static InventoryItem getItem(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("inventory_id");
        String item = resultSet.getString("description");
        int quantity = resultSet.getInt("quantity");
        String location = resultSet.getString("location_name");
        return new InventoryItem(id, item, quantity, location);
    }
}
