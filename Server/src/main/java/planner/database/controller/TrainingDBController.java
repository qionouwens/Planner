package planner.database.controller;

import planner.commons.Streef;
import planner.commons.Training;
import planner.commons.TrainingPart;
import planner.commons.helper.DateConversion;
import planner.database.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TrainingDBController {
    private final static Connection connection = new Connect().getConnection();

    public static List<Training> getTraining() {
        String sql = "SELECT training_id, training_type, description, year, month, day " +
                "FROM training t " +
                "JOIN dateTable dT on dT.date_id = t.date_id " +
                "ORDER BY year DESC, month DESC, day DESC ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            List<Training> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getTraining(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Streef> getStreef() {
        String sql = "SELECT training, streef_value " +
                "FROM streef";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.getResultSet();
            List<Streef> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getStreef(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTraining(Training training) {
        String sql = "INSERT INTO training (training_type, description, date_id) " +
                "VALUES (?, ?, ?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, training.getTrainingType());
            stmt.setString(2, training.getDescription());
            DateDBController dateDBController = new DateDBController();
            int[] dates = DateConversion.getDateArray(training.getCalendar());
            int date_id = dateDBController.getId(dates[0], dates[1], dates[2]);
            stmt.setInt(3, date_id);
            stmt.execute();
            int training_id = getTrainingId(training, date_id);
            for (TrainingPart trainingPart : training.getTrainingParts()) {
                addTrainingParts(training_id, trainingPart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addStreef(Streef streef) {
        String sql = "INSERT INTO streef (training, streef_value) VALUES (?, ?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, streef.getTraining());
            stmt.setString(2, streef.getStreef());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateStreef(Streef streef) {
        String sql = "UPDATE streef " +
                "SET streef_value = ? " +
                "WHERE training = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, streef.getStreef());
            stmt.setString(2, streef.getTraining());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTraining(Training training) {
        String sql = "UPDATE training " +
                "SET training_type = ?, " +
                "   description = ? " +
                "WHERE training_id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, training.getTrainingType());
            stmt.setString(2, training.getDescription());
            stmt.setInt(3, training.getId());
            stmt.execute();
            for (TrainingPart trainingPart : training.getTrainingParts()) {
                updateTrainingPart(trainingPart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTrainingPart(TrainingPart trainingPart) {
        String sql = "UPDATE trainingPart " +
                "SET distance = ?, " +
                "time_spent = ? " +
                "WHERE training_part_id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, trainingPart.getDistance());
            stmt.setString(2, trainingPart.getTime());
            stmt.setInt(3, trainingPart.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addTrainingParts(int trainingId, TrainingPart trainingPart) {
        String sql = "INSERT INTO trainingPart (training_id, distance, time_spent) " +
                "VALUES (?, ?, ?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, trainingId);
            stmt.setInt(2, trainingPart.getDistance());
            stmt.setString(3, trainingPart.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getTrainingId(Training training, int date_id) {
        String sql = "SELECT training_id " +
                "FROM training " +
                "WHERE training_type = ? AND description = ? AND date_id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, training.getTrainingType());
            stmt.setString(2, training.getDescription());
            stmt.setInt(3, date_id);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.getInt("training_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<TrainingPart> getTrainingParts(int id) {
        String sql = "SELECT training_part_id, distance, time_spent " +
                "FROM trainingPart " +
                "WHERE training_id = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            List<TrainingPart> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getTrainingPart(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static TrainingPart getTrainingPart(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("training_part_id");
        int distance = resultSet.getInt("distance");
        String time = resultSet.getString("time_spent");
        return new TrainingPart(id, distance, time);
    }

    private static Training getTraining(ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("training_type");
        String description = resultSet.getString("description");
        int year = resultSet.getInt("year");
        int month = resultSet.getInt("month");
        int day = resultSet.getInt("day");
        GregorianCalendar calendar = DateConversion.getDate(year, month, day);
        int id = resultSet.getInt("training_id");
        List<TrainingPart> trainingParts = getTrainingParts(id);
        return new Training(id, type, description, calendar, trainingParts);
    }

    public static Streef getStreef(ResultSet resultSet) throws SQLException {
        String training = resultSet.getString("training");
        String streef = resultSet.getString("streef_value");
        return new Streef(training, streef);
    }
}
