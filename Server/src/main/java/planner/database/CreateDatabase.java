package planner.database;

import planner.database.controller.DateDBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class CreateDatabase {
    private static final Connection connection = new Connect().getConnection();

    public static void create() {
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
        createSleep();
        createStreef();
        addCleaningTasks();
        updateCategories();
        initialiseGroceryTypes();
        setPriorities();
        setLocation();
    }

    private static void createCalendarItem() {
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

    private static void createDateTable() {
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

    private static void createTodo() {
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

    private static void createExpenseCategory() {
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

    private static void createIncomeExpense() {
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

    private static void createCleaningTask() {
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

    private static void createGrocery() {
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

    private static void createPriority() {
        String sql = "CREATE TABLE IF NOT EXISTS priority ( " +
                "priority_id INTEGER PRIMARY KEY, " +
                "level TEXT UNIQUE " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("priority");
        }
    }

    private static void createGroceryType() {
        String sql = "CREATE TABLE IF NOT EXISTS groceryType ( " +
                "type_id INTEGER PRIMARY KEY, " +
                "label TEXT UNIQUE " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("groctype");
        }
    }

    private static void createInventory() {
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

    private static void createLocation() {
        String sql = "CREATE TABLE IF NOT EXISTS location ( " +
                "location_id INTEGER PRIMARY KEY, " +
                "location_name TEXT UNIQUE " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("loca");
        }
    }

    private static void createUpdate() {
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

    private static void createUpdateCategory() {
        String sql = "CREATE TABLE IF NOT EXISTS updateCategory ( " +
                "category_id INTEGER PRIMARY KEY, " +
                "name TEXT UNIQUE" +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("updatecat");
        }
    }

    private static void createTraining() {
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

    private static void createTrainingPart() {
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

    private static void createWeight() {
        String sql = "CREATE TABLE IF NOT EXISTS weight ( " +
                "date_id TEXT PRIMARY KEY, " +
                "weight_value INTEGER," +
                "FOREIGN KEY (date_id) " +
                "   REFERENCES dateTable (date_id) " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("weight");
        }
    }

    private static void createSleep() {
        String sql = "CREATE TABLE IF NOT EXISTS sleep ( " +
                "date_id TEXT PRIMARY KEY, " +
                "sleep_hour INTEGER," +
                "sleep_minute INTEGER," +
                "FOREIGN KEY (date_id) " +
                "   REFERENCES dateTable (date_id) " +
                ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("steps");
        }
    }

    private static void createStreef() {
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

    private static void addCleaningTask(String task, int frequency, int date_id) {
        String sql = "INSERT OR IGNORE INTO cleaningTask (task, frequency, date_id) " +
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

    private static void addCleaningTasks() {
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

    private static void updateCategory(String name) {
        String sql = "INSERT OR IGNORE INTO updateCategory (name) " +
                "    VALUES(?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateCategories() {
        updateCategory("Wakker voor 10:00");
        updateCategory("Gaan slapen voor 00:00");
        updateCategory("Dagplanning gemaakt");
        updateCategory("10000 stappen");
        updateCategory("Gewogen");
        updateCategory("Slaap Ingevoerd");
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

    public static void insertGroceryType(String type) {
        String sql = "INSERT OR IGNORE INTO groceryType (label) " +
                "VALUES(?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, type);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initialiseGroceryTypes() {
        insertGroceryType("Grocery");
        insertGroceryType("Misc");
    }

    public static void insertPriority(String priority) {
        String sql = "INSERT OR IGNORE INTO priority (level) " +
                "VALUES (?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, priority);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setPriorities() {
        insertPriority("High");
        insertPriority("Medium");
        insertPriority("Low");
    }

    public static void insertLocation(String location) {
        String sql = "INSERT OR IGNORE INTO location (location_name) " +
                "VALUES(?) ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, location);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setLocation() {
        insertLocation("Fridge");
        insertLocation("Closets");
        insertLocation("Misc");
    }

    public static void main(String[] args) {
        create();
    }
}
