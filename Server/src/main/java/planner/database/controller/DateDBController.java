package planner.database.controller;

import planner.commons.helper.DateConversion;
import planner.database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class DateDBController {
    private final Connect connection;

    public DateDBController() {
        connection = new Connect();
    }
    public void add(int year, int month, int day) {
        String sql = "INSERT INTO dateTable (day, month, year) " +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setInt(1, day);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("DateAdd");
        }
    }

    public ResultSet getResultSetDate(int year, int month, int day) {
        String sql = "SELECT * FROM dateTable " +
                "WHERE year = ? " +
                "AND month = ? " +
                "AND day = ? ";
        try {
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            stmt.setInt(3, day);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("DateAdd");
        }
        return null;
    }

    public GregorianCalendar getById(int id) {
        String sql = "SELECT * FROM dateTable WHERE date_id = ? ";
        try {
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            return DateConversion.getDate(
                    result.getInt("year"), result.getInt("month"), result.getInt("day"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
