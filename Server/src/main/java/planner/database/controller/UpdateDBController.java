package planner.database.controller;

import planner.commons.UpdateDay;
import planner.commons.helper.DateConversion;
import planner.database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class UpdateDBController {
    private final Connect connect = new Connect();
    private final DateDBController dateDBController = new DateDBController();

    public UpdateDBController() {

    }

    public List<String> getCategories() {
        String sql = "SELECT name FROM updateCategory ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            List<String> stringList = new ArrayList<>();
            while (result.next()) {
                stringList.add(result.getString("name"));
            }
            return stringList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UpdateDay getForDay(int year, int month, int day) {
        String sql = "SELECT name " +
                "FROM updateTable ut " +
                "JOIN updateCategory uc ON ut.category_id = uc.category_id " +
                "JOIN dateTable dt ON ut.date_id = dt.date_id " +
                "WHERE dt.year = ? AND dt.month = ? AND dt.day = ? ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            stmt.setInt(3, day);
            ResultSet result = stmt.executeQuery();
            List<String> stringList = new ArrayList<>();
            while (result.next()) {
                stringList.add(result.getString("name"));
            }
            return new UpdateDay(DateConversion.getDate(year, month, day), stringList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCategoryId(String name) {
        String sql = "SELECT category_id " +
                "FROM updateCategory " +
                "WHERE name = ? ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.getInt("category_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String name, int year, int month, int day) {
        String sql = "DELETE FROM updateTable " +
                "WHERE date_id = ? AND category_id = ? ";
        try {
            int date_id = dateDBController.getId(year, month, day);
            int category_id = getCategoryId(name);
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, date_id);
            stmt.setInt(2, category_id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String name, int year, int month, int day) {
        String sql = "INSERT INTO updateTable(date_id, category_id) " +
                "VALUES(?, ?) ";
        try {
            PreparedStatement stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, dateDBController.getId(year, month, day));
            stmt.setInt(2, getCategoryId(name));
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(UpdateDay updateDay) {
        GregorianCalendar calendar = updateDay.getCalendar();
        int[] dates = DateConversion.getDateArray(calendar);
        List<String> inDB = getForDay(dates[0], dates[1], dates[2]).getCategoryMap();
        List<String> inUpdate = updateDay.getCategoryMap();
        List<String> intersection = new ArrayList<>();
        for (String str : inDB) {
            if (inUpdate.contains(str)) {
                intersection.add(str);
            }
        }
        inDB.removeAll(intersection);
        inUpdate.removeAll(intersection);
        for (String str : inDB) {
            delete(str, dates[0], dates[1], dates[2]);
        }
        for (String str : inUpdate) {
            insert(str, dates[0], dates[1], dates[2]);
        }
    }
}
