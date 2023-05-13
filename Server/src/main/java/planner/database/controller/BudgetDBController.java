package planner.database.controller;

import planner.commons.ResultCategory;
import planner.commons.Statement;
import planner.commons.StatementCategory;
import planner.commons.helper.DateConversion;
import planner.database.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class BudgetDBController {
    Connect connect = new Connect();
    DateDBController dateDBController = new DateDBController();

    public BudgetDBController() {}

    public List<Statement> getMostRecent() {
        String sql = "SELECT e.id, e.isIncome, e.amount, d.year, d.month, d.day, c.name " +
                "     FROM incomeExpense e " +
                "     JOIN dateTable d ON e.date_id = d.date_id " +
                "     JOIN expenseCategory c ON c.category_id = e.category_id " +
                "     ORDER BY " +
                "             d.year DESC, d.month DESC, d.day DESC" +
                "     LIMIT 50  ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            return getStatementList(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StatementCategory> getCategories() {
        String sql = "SELECT * FROM expenseCategory ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            return getCategoryList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ResultCategory> getResultCategory(int year, int month) {
        String sql = "SELECT c.category_id, name, SUM(amount) AS total, budget " +
                "   FROM expenseCategory c" +
                "       JOIN incomeExpense e ON c.category_id = e.category_id" +
                "       JOIN dateTable d ON e.date_id = d.date_id" +
                "   WHERE year = ? AND month = ? AND isIncome = 0 " +
                "   GROUP BY name ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet resultSet = stmt.executeQuery();
            return getResultList(resultSet, month, false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ResultCategory> getIncomeResult(int year, int month) {
        String sql = "SELECT c.category_id, name, SUM(amount) AS total, budget " +
                "   FROM expenseCategory c" +
                "       JOIN incomeExpense e ON c.category_id = e.category_id" +
                "       JOIN dateTable d ON e.date_id = d.date_id" +
                "   WHERE year = ? AND month = ? AND isIncome = 1 " +
                "   GROUP BY name ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet resultSet = stmt.executeQuery();
            return getResultList(resultSet, month, true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStatement(Statement statement) {
        String sql = "INSERT INTO incomeExpense (isIncome, amount, category_id, date_id) " +
                "     VALUES (?, ?, ?, ?) ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            int isIncome = statement.isIncome() ? 1 : 0;
            stmt.setInt(1, isIncome);
            stmt.setInt(2, statement.getAmount());
            stmt.setInt(3, getCategoryId(statement.getCategory()));
            GregorianCalendar calendar = statement.getDate();
            int[] dates = DateConversion.getDateArray(calendar);
            stmt.setInt(4, dateDBController.getId(dates[0], dates[1], dates[2]));
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategory(StatementCategory category) {
        String sql = "INSERT INTO expenseCategory (name, budget) " +
                "   VALUES (?, ?) ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getBudget());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCategory(StatementCategory category) {
        String sql = "INSERT INTO expenseCategory (name, budget) " +
                "   VALUES (?, ?) ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getBudget());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatement(Statement statement) {
        String sql = " UPDATE incomeExpense " +
                "      SET isIncome = ?, " +
                "          amount = ?, " +
                "          category_id = ?, " +
                "          date_id = ? " +
                "      WHERE id = ? ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            int isIncome = statement.isIncome() ? 1 : 0;
            stmt.setInt(1, isIncome);
            stmt.setInt(2, statement.getAmount());
            stmt.setInt(3, getCategoryId(statement.getCategory()));
            int[] dates = DateConversion.getDateArray(statement.getDate());
            stmt.setInt(4, dateDBController.getId(dates[0], dates[1], dates[2]));
            stmt.setInt(5, statement.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStatement(Statement statement) {
        String sql = "DELETE FROM incomeExpense WHERE id = ? ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            stmt.setInt(1, statement.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getCategoryId(String name) {
        String sql = "SELECT * FROM expenseCategory WHERE name = ? ";
        try {
            PreparedStatement stmt;
            stmt = connect.getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();
            return result.getInt("category_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private StatementCategory getCategory(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("category_id");
        String name = resultSet.getString("name");
        int budget = resultSet.getInt("budget");
        return new StatementCategory(id, name, budget);
    }

    private List<StatementCategory> getCategoryList(ResultSet resultSet) throws SQLException {
        List<StatementCategory> statementCategories = new ArrayList<>();
        while (resultSet.next()) {
            statementCategories.add(getCategory(resultSet));
        }
        return statementCategories;
    }

    private ResultCategory getResult(ResultSet resultSet, int month, boolean isIncome) throws SQLException {
        int id = resultSet.getInt("category_id");
        String name = resultSet.getString("name");
        int total = resultSet.getInt("total");
        int budget = resultSet.getInt("budget");
        return new ResultCategory(id, name, budget, month, total, isIncome);
    }

    private List<ResultCategory>  getResultList(ResultSet resultSet, int month, boolean isIncome) throws SQLException {
        List<ResultCategory> resultCategories = new ArrayList<>();
        while (resultSet.next()) {
            resultCategories.add(getResult(resultSet, month, isIncome));
        }
        return resultCategories;
    }

    private Statement getStatement(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        boolean isIncome = resultSet.getInt("isIncome") == 1;
        int amount = resultSet.getInt("amount");
        String category = resultSet.getString("name");
        int year = resultSet.getInt("year");
        int month = resultSet.getInt("month");
        int day = resultSet.getInt("day");
        GregorianCalendar calendar = DateConversion.getDate(year, month, day);
        return new Statement(id, isIncome, amount, category, calendar);
    }

    private List<Statement> getStatementList(ResultSet resultSet) throws SQLException {
        List<Statement> statements = new ArrayList<>();
        while (resultSet.next()) {
            statements.add(getStatement(resultSet));
        }
        return statements;
    }
}
