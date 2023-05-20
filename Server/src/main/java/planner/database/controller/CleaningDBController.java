package planner.database.controller;

import planner.commons.CleaningTask;
import planner.commons.helper.DateConversion;
import planner.database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CleaningDBController {
    private final Connect connect = new Connect();
    private final DateDBController dateDBController = new DateDBController();

    public List<CleaningTask> getTasks() {
        String sql = "SELECT task, frequency, year, month, day " +
                "    FROM cleaningTask c " +
                "       JOIN dateTable dT on c.date_id = dT.date_id ; ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            ResultSet result = stmt.getResultSet();
            return getListFromResult(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doTask(String task) {
        LocalDateTime now = LocalDateTime.now();
        String sql = "UPDATE cleaningTask " +
                " SET date_id = ? " +
                " WHERE task = ?; ";
        try {
            int date_id = dateDBController.getId(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, date_id);
            stmt.setString(2, task);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private List<CleaningTask> getListFromResult(ResultSet result) throws SQLException {
        List<CleaningTask> cleaningTasks = new ArrayList<>();
        while (result.next()) {
            cleaningTasks.add(getTaskFromResult(result));
        }
        return cleaningTasks;
    }

    private CleaningTask getTaskFromResult(ResultSet result) throws SQLException {
        String task = result.getString("task");
        int frequency = result.getInt("frequency");
        int year = result.getInt("year");
        int month = result.getInt("month");
        int day = result.getInt("day");
        GregorianCalendar calendar = DateConversion.getDate(year, month, day);
        return new CleaningTask(task, frequency, calendar);
    }
}
