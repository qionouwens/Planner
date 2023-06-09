package planner.client.sceneControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import planner.client.MainUICtrl;
import planner.client.serverUtils.*;
import planner.client.views.CalendarItemView;
import planner.client.views.DailyScheduleView;
import planner.commons.*;
import planner.commons.helper.DateConversion;

import java.time.LocalDateTime;
import java.util.*;

public class HomeScreenCtrl {
    private static class CalendarItemComparator implements Comparator<CalendarItem> {
        @Override
        public int compare(CalendarItem o1, CalendarItem o2) {
            return o1.compareTo(o2);
        }
    }

    private ObservableList<ResultCategory> categories;

    @FXML
    private Label monday;
    @FXML
    private Label tuesday;
    @FXML
    private Label wednesday;
    @FXML
    private Label thursday;
    @FXML
    private Label friday;
    @FXML
    private Label saturday;
    @FXML
    private Label sunday;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane calendarPane;
    @FXML
    private ScrollPane daily;
    @FXML
    private TableView<ResultCategory> table;
    @FXML
    private TableColumn<ResultCategory, String> category;
    @FXML
    private TableColumn<ResultCategory, Integer> budget;
    @FXML
    private TableColumn<ResultCategory, Integer> spend;
    @FXML
    private TableColumn<ResultCategory, Integer> left;
    @FXML
    private GridPane cleaningGrid;
    @FXML
    private ProgressIndicator progress;
    @FXML
    private Button groceryButton;
    @FXML
    private Button miscButton;
    @FXML
    private Button weightButton;
    @FXML
    private Button sleepButton;
    @FXML
    private Label todayLabel;
    @FXML
    private Label days;
    @FXML
    private Label week;
    @FXML
    private Label month;
    @FXML
    private Label quarter;
    @FXML
    private Label halfYear;
    @FXML
    private Label year;
    @FXML
    private Label ever;
    @FXML
    private Button todayButton;
    @FXML
    private Button daysButton;
    @FXML
    private Button weekButton;
    @FXML
    private Button monthButton;
    @FXML
    private Button quarterButton;
    @FXML
    private Button halfYearButton;
    @FXML
    private Button yearButton;
    @FXML
    private Button everButton;
    private List<Todo> todayList;
    private List<Todo> daysList;
    private List<Todo> weekList;
    private List<Todo> monthList;
    private List<Todo> quarterList;
    private List<Todo> halfYearList;
    private List<Todo> yearList;
    private List<Todo> everList;

    private List<CleaningTask> cleaningTasks;
    private static HomeScreenCtrl INSTANCE;
    private MainUICtrl mainUICtrl;
    private GregorianCalendar today;
    private GregorianCalendar currentDate;

    public HomeScreenCtrl() {
        INSTANCE = this;
    }

    public void initialise(MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
        LocalDateTime now = LocalDateTime.now();
        today = DateConversion.getDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        currentDate = DateConversion.getDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        CleaningServerUtils cleaningServerUtils = new CleaningServerUtils();
        cleaningTasks = cleaningServerUtils.getTasks();
        initialiseTable();
        initialiseButtons();
        initialiseTodoList();
        setTable();
        setWeek();
        setHours();
        setCalendarItems();
        setDailySchedule();
        setCleaningGrid();
        setDailyScreen();
    }

    public void initialiseButtons() {
        groceryButton.setOnAction(actionEvent -> mainUICtrl.addGrocery("Grocery"));
        miscButton.setOnAction(actionEvent -> mainUICtrl.addGrocery("Misc"));
        weightButton.setOnAction(actionEvent -> mainUICtrl.addValue("weight"));
        sleepButton.setOnAction(actionEvent -> mainUICtrl.addValue("sleep"));
        todayButton.setOnAction(actionEvent -> showTodo("today"));
        daysButton.setOnAction(actionEvent -> showTodo("days"));
        weekButton.setOnAction(actionEvent -> showTodo("week"));
        monthButton.setOnAction(actionEvent -> showTodo("month"));
        quarterButton.setOnAction(actionEvent -> showTodo("quarter"));
        halfYearButton.setOnAction(actionEvent -> showTodo("halfYear"));
        yearButton.setOnAction(actionEvent -> showTodo("year"));
        everButton.setOnAction(actionEvent -> showTodo("ever"));
    }

    public void initialiseTodoList() {
        GregorianCalendar todayClone = DateConversion.getGregorianCalendarClone(today);
        GregorianCalendar daysClone = DateConversion.getGregorianCalendarClone(today);
        daysClone.add(Calendar.DAY_OF_MONTH, 3);
        GregorianCalendar weeksClone = DateConversion.getGregorianCalendarClone(today);
        daysClone.add(Calendar.DAY_OF_MONTH, 7);
        GregorianCalendar monthClone = DateConversion.getGregorianCalendarClone(today);
        daysClone.add(Calendar.MONTH, 1);
        GregorianCalendar quarterClone = DateConversion.getGregorianCalendarClone(today);
        daysClone.add(Calendar.MONTH, 3);
        GregorianCalendar halfYearClone = DateConversion.getGregorianCalendarClone(today);
        daysClone.add(Calendar.MONTH, 6);
        GregorianCalendar yearClone = DateConversion.getGregorianCalendarClone(today);
        daysClone.add(Calendar.YEAR, 1);
        todayList = new ArrayList<>();
        daysList = new ArrayList<>();
        weekList = new ArrayList<>();
        monthList = new ArrayList<>();
        quarterList = new ArrayList<>();
        halfYearList = new ArrayList<>();
        yearList = new ArrayList<>();
        everList = new ArrayList<>();
        List<Todo> todoList = TodoServerUtils.getTodos();
        for (Todo todo : todoList) {
            if (todo.getDate().compareTo(todayClone) < 0) {
                everList.add(todo);
            } else if (todo.getDate().compareTo(todayClone) == 0) {
                todayList.add(todo);
            } else if (todo.getDate().compareTo(daysClone) <= 0) {
                daysList.add(todo);
            } else if (todo.getDate().compareTo(weeksClone) <= 0) {
                weekList.add(todo);
            } else if (todo.getDate().compareTo(monthClone) <= 0) {
                monthList.add(todo);
            } else if (todo.getDate().compareTo(quarterClone) <= 0) {
                quarterList.add(todo);
            } else if (todo.getDate().compareTo(halfYearClone) <= 0) {
                halfYearList.add(todo);
            } else if (todo.getDate().compareTo(yearClone) <= 0) {
                yearList.add(todo);
            } else {
                everList.add(todo);
            }
        }
        todayLabel.setText(todayList.size() + " task(s)");
        days.setText(daysList.size() + " task(s)");
        week.setText(weekList.size() + " task(s)");
        month.setText(monthList.size() + " task(s)");
        quarter.setText(quarterList.size() + " task(s)");
        halfYear.setText(halfYearList.size() + " task(s)");
        year.setText(yearList.size() + " task(s)");
        ever.setText(everList.size() + " task(s)");
    }

    public void showTodo(String button) {
        switch (button) {
            case "today" -> mainUICtrl.seeTodo(todayList);
            case "days" -> mainUICtrl.seeTodo(daysList);
            case "weeks" -> mainUICtrl.seeTodo(weekList);
            case "month" -> mainUICtrl.seeTodo(monthList);
            case "quarter" -> mainUICtrl.seeTodo(quarterList);
            case "halfYear" -> mainUICtrl.seeTodo(halfYearList);
            case "year" -> mainUICtrl.seeTodo(yearList);
            case "ever" -> mainUICtrl.seeTodo(everList);
        }
    }

    public static HomeScreenCtrl getINSTANCE() {
        return INSTANCE;
    }

    public void setWeek() {
        GregorianCalendar startOfWeek = DateConversion.getFirstDay(currentDate);
        monday.setText(turnIntoString(startOfWeek));
        startOfWeek.add(Calendar.DAY_OF_MONTH, 1);
        tuesday.setText(turnIntoString(startOfWeek));
        startOfWeek.add(Calendar.DAY_OF_MONTH, 1);
        wednesday.setText(turnIntoString(startOfWeek));
        startOfWeek.add(Calendar.DAY_OF_MONTH, 1);
        thursday.setText(turnIntoString(startOfWeek));
        startOfWeek.add(Calendar.DAY_OF_MONTH, 1);
        friday.setText(turnIntoString(startOfWeek));
        startOfWeek.add(Calendar.DAY_OF_MONTH, 1);
        saturday.setText(turnIntoString(startOfWeek));
        startOfWeek.add(Calendar.DAY_OF_MONTH, 1);
        sunday.setText(turnIntoString(startOfWeek));
    }

    private String turnIntoString(GregorianCalendar c) {
        return c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1);
    }

    public void calendarOverview() {
        mainUICtrl.showCalendarOverview();
    }

    public void setHours() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                Label label = new Label((j+9) + ":00");
                label.setPrefSize(103, 49);
                label.setAlignment(Pos.TOP_LEFT);
                gridPane.add(label, i, j+1);
            }
        }
    }

    public void setCalendarItems() {
        calendarPane.getChildren().clear();
        CalendarServerUtils calendarServerUtils = new CalendarServerUtils();
        int[] date = DateConversion.getDateArray(currentDate);
        List<CalendarItem> calendarItems = calendarServerUtils.getCalendarForWeek(date[0], date[1], date[2]);
        for (CalendarItem calendarItem : calendarItems) {
            if (calendarItem != null) {
                calendarPane.getChildren().add(new CalendarItemView(calendarItem, mainUICtrl, true));
            }
        }
        addButtons();
    }

    public void setDailySchedule() {
        CalendarServerUtils calendarServerUtils = new CalendarServerUtils();
        int[] dates = DateConversion.getDateArray(today);
        List<CalendarItem> calendarItems = calendarServerUtils.getCalendarForDay(dates[0], dates[1], dates[2]);
        calendarItems.sort(new CalendarItemComparator());
        DailyScheduleView dailyScheduleView = new DailyScheduleView(calendarItems, mainUICtrl);
        daily.setContent(dailyScheduleView);
    }

    public void addButtons() {
        Button prevButton = new Button("<");
        prevButton.setPrefSize(30, 30);
        prevButton.setLayoutX(10);
        prevButton.setLayoutY(200);
        prevButton.setOnAction(event -> prevWeek());
        calendarPane.getChildren().add(prevButton);
        Button nextButton = new Button(">");
        nextButton.setPrefSize(30, 30);
        nextButton.setLayoutX(680);
        nextButton.setLayoutY(200);
        nextButton.setOnAction(event -> nextWeek());
        calendarPane.getChildren().add(nextButton);
        Button addButton = new Button("+");
        addButton.setPrefSize(30, 30);
        addButton.setLayoutX(660);
        addButton.setLayoutY(400);
        addButton.setOnAction(event -> addButton());
        calendarPane.getChildren().add(addButton);
    }

    public void initialiseTable() {
        category.setCellValueFactory(stat -> new SimpleStringProperty(stat.getValue().getName()));
        budget.setCellValueFactory(stat -> new SimpleIntegerProperty(stat.getValue().getBudget() / 100).asObject());
        spend.setCellValueFactory(stat -> new SimpleIntegerProperty((stat.getValue().getAmount()) / 100).asObject());
        left.setCellValueFactory(stat -> new SimpleIntegerProperty(
                (stat.getValue().getBudget() - stat.getValue().getAmount()) / 100).asObject());
    }

    public void setTable() {
        BudgetServerUtils budgetServerUtils = new BudgetServerUtils();
        LocalDateTime now = LocalDateTime.now();
        List<ResultCategory> results = budgetServerUtils.getResultForThisMonth(now.getYear(), now.getMonthValue());
        categories = FXCollections.observableList(results);
        table.setItems(categories);
    }

    public void setCleaningGrid() {
        cleaningGrid.getChildren().clear();
        cleaningGrid.setGridLinesVisible(true);
        for (int i = 0; i < 12; i++) {
            CleaningTask task = cleaningTasks.get(i);
            Label name = new Label(task.getName());
            name.setPrefSize(119, 39);
            name.setFont(new Font(22));
            GregorianCalendar lastDone = task.getLast_done();
            Label lastDoneDate = new Label(DateConversion.getStringFromDate(lastDone));
            lastDoneDate.setPrefSize(119, 39);
            lastDoneDate.setFont(new Font(22));
            GregorianCalendar nextDo = task.getLast_done();
            nextDo.add(Calendar.DAY_OF_MONTH, task.getFrequency());
            Label nextDoDate = new Label(DateConversion.getStringFromDate(nextDo));
            nextDoDate.setPrefSize(119, 39);
            nextDoDate.setFont(new Font(22));
            int compare = nextDo.compareTo(today);
            String color = compare > 0 ? "lime" : "red";
            name.setStyle("-fx-background-color: " + color);
            nextDoDate.setStyle("-fx-background-color: " + color);
            lastDoneDate.setStyle("-fx-background-color: " + color);
            cleaningGrid.add(name, 0, i);
            cleaningGrid.add(nextDoDate, 1, i);
            cleaningGrid.add(lastDoneDate, 2, i);
        }
    }

    public void setDailyScreen() {
        UpdateServerUtils updateServerUtils = new UpdateServerUtils();
        List<String> categories = updateServerUtils.getCategories();
        int[] dates = DateConversion.getDateArray(today);
        UpdateDay updateDay = updateServerUtils.getUpdateDay(dates[0], dates[1], dates[2]);
        progress.setProgress((double) updateDay.getCategoryMap().size() / categories.size());
    }

    public void changeTask() {
        for (int i = 0; i < 12; i++) {
            Button button = new Button("Gedaan");
            button.setPrefSize(220, 32);
            CleaningServerUtils cleaningServerUtils = new CleaningServerUtils();
            int finalI = i;
            button.setOnAction(event -> {
                cleaningServerUtils.doTask(cleaningTasks.get(finalI).getName());
                initialise(mainUICtrl);
            });
            cleaningGrid.add(button, 1, i, 2, 1);
        }
    }

    public void prevWeek() {
        currentDate.add(Calendar.DAY_OF_MONTH, -7);
        setWeek();
        setCalendarItems();
    }

    public void nextWeek() {
        currentDate.add(Calendar.DAY_OF_MONTH, 7);
        setWeek();
        setCalendarItems();
    }

    public void addButton() {
        mainUICtrl.showAddCalendar();
    }

    public void changeBudget() {
        mainUICtrl.showBudget();
    }

    public void seeExpenses() {
        mainUICtrl.showExpenses();
    }

    public void showYearView() {mainUICtrl.showYearView();}

    public void showUpdateView() {
        mainUICtrl.showUpdateQuestions();
    }

    public void addTodo() {
        mainUICtrl.addTodo();
    }

    public void seeTrainingOverview() {
        mainUICtrl.seeTrainingOverview();
    }
}
