package planner.database.controller;

import planner.database.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ValueDBController {
    private static Connection connection = new Connect().getConnection();

    public static int getLatestWeight() {
        String sql = "SELECT weight_value " +
                "FROM weight w " +
                "JOIN dateTable dT ON w.date_id = dT.date_id " +
                "ORDER BY year DESC, month DESC, day DESC " +
                "LIMIT 1 ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.getInt("weight_value");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLatestSleep() {
        String sql = "SELECT sleep_hour, sleep_minute " +
                "FROM sleep s " +
                "JOIN dateTable dT ON s.date_id = dT.date_id " +
                "ORDER BY year DESC, month DESC, day DESC " +
                "LIMIT 1 ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            int hour = resultSet.getInt("sleep_hour");
            int minute = resultSet.getInt("sleep_minute");
            return hour + ":" + minute;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateWeight(int weight) {
        String sql = "INSERT INTO weight (date_id, weight_value) " +
                "VALUES (?, ?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, getTodayId());
            stmt.setInt(2, weight);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateSleep(String sleep) {
        Scanner scanner = new Scanner(sleep);
        scanner.useDelimiter(":");
        int hour = scanner.nextInt();
        int minute = scanner.nextInt();
        String sql = "INSERT INTO sleep (date_id, sleep_hour, sleep_minute) " +
                "VALUES(?, ?, ?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, getTodayId());
            stmt.setInt(2, hour);
            stmt.setInt(3, minute);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getTodayId() throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        DateDBController dateDBController = new DateDBController();
        return dateDBController.getId(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
    }
}
