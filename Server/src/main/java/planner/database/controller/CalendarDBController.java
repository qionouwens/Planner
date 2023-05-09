package planner.database.controller;

import planner.commons.CalendarItem;
import planner.database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CalendarDBController {
    private final Connect connect;
    private final DateDBController dateDBController;

    public CalendarDBController() {
        connect = new Connect();
        dateDBController = new DateDBController();
    }
    public CalendarItem getById(int id) {
        String sql = "SELECT * FROM calendarItem WHERE calendar_id = ? ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            return getFromResultSet(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void add(String title, int year, int month, int day,
                    String startTime, String endTime, String colour) {
        String sql = "INSERT INTO calendarItem (title, date_id, starttime_hour, starttime_minute, endtime_hour, endtime_minute, color) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) ";

        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            ResultSet dateResult = dateDBController.getResultSetDate(year, month, day);
            if (dateResult.getInt(1) == 0) {
                dateDBController.add(year, month, day);
                dateResult = dateDBController.getResultSetDate(year, month, day);
            }
            stmt.setString(1, title);
            stmt.setInt(2, dateResult.getInt(1));
            Scanner start = new Scanner(startTime);
            start.useDelimiter(":");
            stmt.setInt(3, start.nextInt());
            stmt.setInt(4, start.nextInt());
            Scanner end = new Scanner(endTime);
            end.useDelimiter(":");
            stmt.setInt(5, end.nextInt());
            stmt.setInt(6, end.nextInt());
            stmt.setString(7, colour);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId(int year, int month, int day, String startTime) {
        String sql = "SELECT c.calendar_id FROM calendarItem c " +
                "   JOIN dateTable d ON c.date_id = d.date_id " +
                "   WHERE year = ? AND month = ? AND day = ? AND starttime_hour = ? AND starttime_minute = ? ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            stmt.setInt(3, day);
            Scanner start = new Scanner(startTime);
            start.useDelimiter(":");
            stmt.setInt(4, start.nextInt());
            stmt.setInt(5, start.nextInt());
            ResultSet result = stmt.executeQuery();
            return result.getInt("calendar_id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public List<CalendarItem> getMiddleWeek(GregorianCalendar startOfWeek) {
        List<CalendarItem> calendarItems = new ArrayList<>();
        String sql = "SELECT calendar_id, title, c.date_id, starttime_hour, starttime_minute, endtime_hour, endtime_minute, color " +
                " FROM calendarItem c " +
                " JOIN dateTable d ON c.date_id = d.date_id " +
                " WHERE year = ? AND month = ? AND (day > ? OR day = ?) AND (day < ? OR day = ?) ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, startOfWeek.get(Calendar.YEAR));
            stmt.setInt(2, startOfWeek.get(Calendar.MONTH) + 1);
            stmt.setInt(3, startOfWeek.get(Calendar.DAY_OF_MONTH));
            stmt.setInt(4, startOfWeek.get(Calendar.DAY_OF_MONTH));
            stmt.setInt(5, startOfWeek.get(Calendar.DAY_OF_MONTH) + 6);
            stmt.setInt(6, startOfWeek.get(Calendar.DAY_OF_MONTH) + 6);
            ResultSet result = stmt.executeQuery();
            calendarItems = getListFromResultSet(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return calendarItems;
    }

    public List<CalendarItem> getEndOfMonth(GregorianCalendar startOfWeek) {
        List<CalendarItem> calendarItems = new ArrayList<>();
        String sql = "SELECT calendar_id, title, c.date_id, starttime_hour, starttime_minute, endtime_hour, endtime_minute, color " +
                " FROM calendarItem c " +
                " JOIN dateTable d ON c.date_id = d.date_id " +
                " WHERE ((year = ? AND month = ? AND (day > ? OR day = ?) AND (day < ? OR day = ?)) OR " +
                "       (year = ? AND month = ? AND (day > 1 OR day = 1) AND (day < ? OR day = ?))) ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, startOfWeek.get(Calendar.YEAR));
            stmt.setInt(2, startOfWeek.get(Calendar.MONTH) + 1);
            stmt.setInt(3, startOfWeek.get(Calendar.DAY_OF_MONTH));
            stmt.setInt(4, startOfWeek.get(Calendar.DAY_OF_MONTH));
            stmt.setInt(5, startOfWeek.getActualMaximum(Calendar.DAY_OF_MONTH));
            stmt.setInt(6, startOfWeek.getActualMaximum(Calendar.DAY_OF_MONTH));
            startOfWeek.add(Calendar.DATE, 6);
            stmt.setInt(7, startOfWeek.get(Calendar.YEAR));
            stmt.setInt(8, startOfWeek.get(Calendar.MONTH) + 1);
            stmt.setInt(9, startOfWeek.get(Calendar.DAY_OF_MONTH));
            stmt.setInt(10, startOfWeek.get(Calendar.DAY_OF_MONTH));
            ResultSet result = stmt.executeQuery();
            calendarItems = getListFromResultSet(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return calendarItems;
    }

    private CalendarItem getFromResultSet(ResultSet result) {
        try {
            if (result.getInt(1) == 0) {
                return null;
            }
            GregorianCalendar calendar = dateDBController.getById(result.getInt("date_id"));
            String startTime = result.getInt("starttime_hour") + ":" + result.getInt("starttime_minute");
            String endTime = result.getInt("endtime_hour") + ":" + result.getInt("endtime_minute");
            return new CalendarItem(result.getInt("calendar_id"), result.getString("title"),
                    calendar, startTime, endTime, result.getString("color"), null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<CalendarItem> getListFromResultSet(ResultSet result) throws SQLException {
        List<CalendarItem> calendarItems = new ArrayList<>();
        boolean cont = result.next();
        do {
            calendarItems.add(getFromResultSet(result));
            cont = result.next();
        } while (cont);
        return calendarItems;
    }
}
