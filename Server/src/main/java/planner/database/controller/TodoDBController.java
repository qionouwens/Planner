package planner.database.controller;

import planner.commons.Todo;
import planner.commons.helper.DateConversion;
import planner.database.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TodoDBController {
    private static final Connection connection = new Connect().getConnection();
    private static final DateDBController dateDBController = new DateDBController();

    public static List<Todo> getTodos() {
        String sql = "SELECT id, description, date_id, calendar_id " +
                "FROM todo";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            List<Todo> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getTodo(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTodo(Todo todo) {
        String sql = "INSERT INTO todo (description, date_id) " +
                "VALUES (?, ?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, todo.getDescription());
            int[] dates = DateConversion.getDateArray(todo.getDate());
            int dateId = dateDBController.getId(dates[0], dates[1], dates[2]);
            stmt.setInt(2, dateId);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTodoToCalendar(int calId, int todoId) {
        String sql = "UPDATE todo " +
                "SET calendar_id = ? " +
                "WHERE id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, calId);
            stmt.setInt(2, todoId);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTodo(int id) {
        String sql = "DELETE FROM todo " +
                "WHERE id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Todo> getForCalendarItem(int id) {
        String sql = "SELECT id, description, date_id, calendar_id " +
                "FROM todo " +
                "WHERE calendar_id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            List<Todo> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getTodo(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Todo getTodo(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String description = resultSet.getString("description");
        int date_id = resultSet.getInt("date_id");
        GregorianCalendar calendar = dateDBController.getById(date_id);
        return new Todo(id, description, calendar);
    }
}
