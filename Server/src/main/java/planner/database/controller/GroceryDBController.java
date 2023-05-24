package planner.database.controller;

import planner.commons.GroceryItem;
import planner.database.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroceryDBController {
    private static Connection connection = new Connect().getConnection();

    public static List<GroceryItem> getAll() {
        String sql = "SELECT grocery_id, description, amount, label, level " +
                "FROM grocery g " +
                "JOIN groceryType gT ON g.type_id = gT.type_id " +
                "JOIN priority p ON g.priority_id = p.priority_id ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            return getGroceries(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getPriorities() {
        String sql = "SELECT level FROM priority ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            List<String> result = new ArrayList<>();
            while(resultSet.next()) {
                result.add(resultSet.getString("level"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addGrocery(GroceryItem groceryItem) {
        String sql = "INSERT INTO grocery (description, amount, priority_id, type_id) " +
                "VALUES (?, ?, ?, ?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, groceryItem.getItem());
            stmt.setInt(2, groceryItem.getQuantity());
            stmt.setInt(3, getPriorityId(groceryItem.getPriority()));
            stmt.setInt(4, getTypeId(groceryItem.getType()));
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteGrocery(int id) {
        String sql = "DELETE FROM grocery " +
                "WHERE grocery_id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getPriorityId(String level) {
        String sql = "SELECT priority_id FROM priority p " +
                "WHERE level = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, level);
            return stmt.executeQuery().getInt("priority_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getTypeId(String label) {
        String sql = "SELECT type_id FROM groceryType " +
                "WHERE label = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, label);
            return stmt.executeQuery().getInt("type_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<GroceryItem> getGroceries(ResultSet resultSet) throws SQLException {
        List<GroceryItem> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(getGrocery(resultSet));
        }
        return result;
    }

    private static GroceryItem getGrocery(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("grocery_id");
        String item = resultSet.getString("description");
        int quantity = resultSet.getInt("amount");
        String priority = resultSet.getString("level");
        String type = resultSet.getString("label");
        return new GroceryItem(id, item, quantity, priority, type);
    }
}
