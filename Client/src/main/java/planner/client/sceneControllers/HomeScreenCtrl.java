package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import planner.client.MainUICtrl;
import planner.client.serverUtils.CalendarServerUtils;
import planner.client.views.CalendarItemView;
import planner.client.views.DailyScheduleView;
import planner.commons.CalendarItem;
import planner.commons.helper.DateConversion;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class HomeScreenCtrl {
    private class CalendarItemComparator implements Comparator<CalendarItem> {
        @Override
        public int compare(CalendarItem o1, CalendarItem o2) {
            return o1.compareTo(o2);
        }
    }
    
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
        setWeek();
        setHours();
        setCalendarItems();
        setDailySchedule();
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
}