package planner.database;

import planner.database.controller.DateDBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class CreateDatabase {
    private final Connection connection;

    public CreateDatabase() {
        connection = new Connect().getConnection();
    }

    public void create() {
        createCalendarItem();
        createDateTable();
        createTodo();
        createExpenseCategory();
        createIncomeExpense();
        createCleaningTask();
        createGrocery();
        createPriority();
        createGroceryType();
        createInventory();
        createLocation();
        createUpdate();
        createUpdateCategory();
        createTraining();
        createTrainingPart();
        createWeight();
        createSteps();
        createStreef();
        addCleaningTasks();
        updateCategories();
    }

    private void createCalendarItem() {
        String sql = "CREATE TABLE IF NOT EXISTS calendarItem ( " +
                "calendar_id INTEGER PRIMARY KEY, " +
                "title TEXT, " +
                "date_id INTEGER, " +
                "starttime_hour INTEGER, " +
                "starttime_minute INTEGER, " +
                "endtime_hour INTEGER, " +
                "endtime_minute INTEGER, " +
                "color TEXT, " +
                "FOREIGN KEY (date_id) " +
                "   REFERENCES dateTable(date_id) " +
                ");";

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("calender");
        }
    }

    private void createDateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS dateTable ( " +
                "date_id INTEGER PRIMARY KEY, " +
                "day INTEGER, " +
                "month INTEGER, " +
                "year INTEGER " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("date");
        }
    }

    private void createTodo() {
        String sql = "CREATE TABLE IF NOT EXISTS todo ( " +
                "id INTEGER PRIMARY KEY, " +
                "description TEXT, " +
                "date_id INTEGER, " +
                "calendar_id INTEGER, " +
                "FOREIGN KEY (calendar_id) " +
                "   REFERENCES calendarItem (calendar_id), " +
                "FOREIGN KEY (date_id) " +
                "   REFERENCES dateTable(date_id) " +
                ");";

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("todo");
        }
    }

    private void createExpenseCategory() {
        String sql = "CREATE TABLE IF NOT EXISTS expenseCategory ( " +
                "category_id INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "budget INTEGER " +
                ");";

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("expcat");
        }
    }

    private void createIncomeExpense() {
        String sql = "CREATE TABLE IF NOT EXISTS incomeExpense ( " +
                "id INTEGER PRIMARY KEY, " +
                "isIncome INTEGER, " +
                "amount INTEGER, " +
                "category_id INTEGER, " +
                "date_id INTEGER, " +
                "FOREIGN KEY (category_id) " +
                "   REFERENCES expenseCategory(category_id), " +
                "FOREIGN KEY (date_id) " +
                "   REFERENCES dateTable(date_id) " +
                ");";

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("inco");
        }
    }

    private void createCleaningTask() {
        String sql = "CREATE TABLE IF NOT EXISTS cleaningTask ( " +
                "task TEXT PRIMARY KEY, " +
                "frequency INTEGER, " +
                "date_id INTEGER, " +
                "FOREIGN KEY (date_id) " +
                "   REFERENCES dateTable (date_id) " +
                ");";

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("cleaning");
        }
    }

    private void createGrocery() {
        String sql = "CREATE TABLE IF NOT EXISTS grocery ( " +
                "grocery_id INTEGER PRIMARY KEY, " +
                "description TEXT, " +
                "amount INTEGER, " +
                "priority_id INTEGER, " +
                "type_id INTEGER, " +
                "FOREIGN KEY (priority_id) " +
                "   REFERENCES priority(priority_id), " +
                "FOREIGN KEY (type_id) " +
                "   REFERENCES groceryType(type_id)" +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("grocery");
        }
    }

    private void createPriority() {
        String sql = "CREATE TABLE IF NOT EXISTS priority ( " +
                "priority_id INTEGER PRIMARY KEY, " +
                "level TEXT " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("priority");
        }
    }

    private void createGroceryType() {
        String sql = "CREATE TABLE IF NOT EXISTS groceryType ( " +
                "type_id INTEGER PRIMARY KEY, " +
                "label TEXT " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("groctype");
        }
    }

    private void createInventory() {
        String sql = "CREATE TABLE IF NOT EXISTS inventory ( " +
                "inventory_id INTEGER PRIMARY KEY, " +
                "description TEXT, " +
                "quantity INTEGER, " +
                "location_id INTEGER, " +
                "FOREIGN KEY (location_id) " +
                "   REFERENCES location (location_id) " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("inventory");
        }
    }

    private void createLocation() {
        String sql = "CREATE TABLE IF NOT EXISTS location ( " +
                "location_id INTEGER PRIMARY KEY, " +
                "location_name TEXT " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("loca");
        }
    }

    private void createUpdate() {
        String sql = "CREATE TABLE IF NOT EXISTS updateTable ( " +
                "date_id INTEGER, " +
                "category_id INTEGER, " +
                "PRIMARY KEY(date_id, category_id), " +
                "FOREIGN KEY (category_id) " +
                "   REFERENCES updateCategory (category_id), " +
                "FOREIGN KEY (date_id) " +
                "   REFERENCES dateTable (date_id) " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update");
        }
    }

    private void createUpdateCategory() {
        String sql = "CREATE TABLE IF NOT EXISTS updateCategory ( " +
                "category_id INTEGER PRIMARY KEY, " +
                "name TEXT " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("updatecat");
        }
    }

    private void createTraining() {
        String sql = "CREATE TABLE IF NOT EXISTS training ( " +
                "training_id INTEGER PRIMARY KEY, " +
                "training_type TEXT, " +
                "description TEXT, " +
                "date_id INTEGER, " +
                "FOREIGN KEY (date_id) " +
                "   REFERENCES dateTable(date_id) " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("traingin");
        }
    }

    private void createTrainingPart() {
        String sql = "CREATE TABLE IF NOT EXISTS trainingPart ( " +
                "training_part_id INTEGER, " +
                "training_id INTEGER, " +
                "distance INTEGER, " +
                "time_spent TEXT, " +
                "PRIMARY KEY(training_part_id, training_id), " +
                "FOREIGN KEY (training_id) " +
                "   REFERENCES training (training_id) " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("trainingpart");
        }
    }

    private void createWeight() {
        String sql = "CREATE TABLE IF NOT EXISTS weight ( " +
                "weighted_date TEXT PRIMARY KEY, " +
                "weight_value INTEGER " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("weight");
        }
    }

    private void createSteps() {
        String sql = "CREATE TABLE IF NOT EXISTS steps ( " +
                "steps_date TEXT PRIMARY KEY, " +
                "step_value INTEGER " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("steps");
        }
    }

    private void createStreef() {
        String sql = "CREATE TABLE IF NOT EXISTS streef ( " +
                "training TEXT PRIMARY KEY, " +
                "streef_value TEXT " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("streef");
        }
    }

    private void addCleaningTask(String task, int frequency, int date_id) {
        String sql = "INSERT INTO cleaningTask (task, frequency, date_id) " +
                " VALUES(?, ?, ?); ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, task);
            stmt.setInt(2, frequency);
            stmt.setInt(3, date_id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addCleaningTasks() {
        LocalDateTime now = LocalDateTime.now();
        DateDBController dateDBController = new DateDBController();
        int date_id;
        try {
            date_id = dateDBController.getId(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addCleaningTask("Afwas", 2, date_id);
        addCleaningTask("Opruimen", 3, date_id);
        addCleaningTask("Stofzuigen", 7, date_id);
        addCleaningTask("Dweilen", 7, date_id);
        addCleaningTask("Badkamer", 14, date_id);
        addCleaningTask("Koelkast", 7, date_id);
        addCleaningTask("Keuken", 28, date_id);
        addCleaningTask("Bed", 84, date_id);
        addCleaningTask("Was", 3, date_id);
        addCleaningTask("Woonkamer", 182, date_id);
        addCleaningTask("Plankjes", 182, date_id);
        addCleaningTask("Ramen", 182, date_id);
    }

    private void updateCategory(String name) {
        String sql = "INSERT INTO updateCategory (name) " +
                "    VALUES(?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCategories() {
        updateCategory("Wakker voor 10:00");
        updateCategory("Gaan slapen voor 00:00");
        updateCategory("Dagplanning gemaakt");
        updateCategory("10000 stappen");
        updateCategory("Gewogen");
        updateCategory("Geen alcohol gehad");
        updateCategory("Geschaakt");
        updateCategory("Gewerkt aan een project");
        updateCategory("Gestudeerd");
        updateCategory("Ontbeten");
        updateCategory("Gelunched");
        updateCategory("Avondgegeten");
        updateCategory("Geen Thuisbezorgd");
        updateCategory("Opgeruimd");
        updateCategory("Schoongemaakt");
        updateCategory("Was gedaan");
        updateCategory("Budget Geupdated");
        updateCategory("Half uur buiten geweest");
        updateCategory("'s ochtends getandenpoetst");
        updateCategory("Geen Frisdrank");
    }

    public static void main(String[] args) {
        CreateDatabase createDatabase = new CreateDatabase();
        createDatabase.create();
    }
}
