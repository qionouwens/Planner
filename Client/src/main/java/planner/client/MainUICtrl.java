package planner.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import planner.client.sceneControllers.*;
import planner.commons.CalendarItem;
import planner.commons.Statement;
import planner.commons.helper.DateConversion;

import java.io.IOException;
import java.time.LocalDateTime;

public class MainUICtrl {
    private final Stage stage;

    public MainUICtrl(Stage stage) {
        this.stage = stage;
    }

    public void showCalendarOverview() {
        String calendarOverview = "CalendarOverview.fxml";
        showStage(calendarOverview, 1440, 1024);
        CalendarOverviewCtrl calendarOverviewCtrl = CalendarOverviewCtrl.getInstance();
        LocalDateTime now = LocalDateTime.now();
        calendarOverviewCtrl.initialise(DateConversion.getDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth()), this);
    }

    public void showAddCalendar() {
        String addCalendar = "AddCalendar.fxml";
        showStage(addCalendar, 450, 700);
        AddCalendarCtrl addCalendarCtrl = AddCalendarCtrl.getInstance();
        addCalendarCtrl.initialise(stage, this);
    }

    public void showCalendar(CalendarItem calendarItem) {
        String seeCalendar = "SeeCalendarItem.fxml";
        showStage(seeCalendar, 450, 700);
        SeeCalendarCtrl seeCalendarCtrl = SeeCalendarCtrl.getINSTANCE();
        seeCalendarCtrl.initialise(stage, this, calendarItem);
    }

    public void showHome() {
        String home = "HomeScreen.fxml";
        showStage(home, 1440, 1024);
        HomeScreenCtrl homeScreenCtrl = HomeScreenCtrl.getINSTANCE();
        homeScreenCtrl.initialise(this);
    }

    public void showBudget() {
        String budget = "Budget.fxml";
        showStage(budget, 406, 400);
        BudgetCtrl budgetCtrl = BudgetCtrl.getInstance();
        budgetCtrl.Initialise(this);
    }

    public void showExpenses() {
        String expenses = "SeeExpenses.fxml";
        showStage(expenses, 720, 1024);
        SeeExpensesCtrl seeExpensesCtrl = SeeExpensesCtrl.getINSTANCE();
        seeExpensesCtrl.initialise(this);
    }

    public void showAddStatement() {
        String addStatement = "AddStatement.fxml";
        showStage(addStatement, 400, 363);
        AddExpenseCtrl addExpenseCtrl = AddExpenseCtrl.getInstance();
        addExpenseCtrl.initialise(this);
    }

    public void showEditStatement(Statement statement) {
        String addStatement = "AddStatement.fxml";
        showStage(addStatement, 400, 363);
        AddExpenseCtrl addExpenseCtrl = AddExpenseCtrl.getInstance();
        addExpenseCtrl.initialise(this, statement);
    }

    public void showYearView() {
        String yearView = "BudgetYearView.fxml";
        showStage(yearView, 1440, 598);
        YearViewCtrl yearViewCtrl = YearViewCtrl.getInstance();
        yearViewCtrl.initialise(this);
    }

    public void showUpdateQuestions() {
        String updateView = "UpdateQuestions.fxml";
        showStage(updateView, 600, 400);
        UpdateQuestionsCtrl updateQuestionsCtrl = UpdateQuestionsCtrl.getInstance();
        LocalDateTime now = LocalDateTime.now();
        updateQuestionsCtrl.initialise(this, DateConversion.getDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth()));
    }

    public void addGrocery(String type) {
        String addGrocery = "AddGrocery.fxml";
        showStage(addGrocery, 308, 356);
        AddGroceryCtrl addGroceryCtrl = AddGroceryCtrl.getInstance();
        addGroceryCtrl.initialise(this, type);
    }

    public void showStage(String fxmlScene, int width, int height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlScene));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
