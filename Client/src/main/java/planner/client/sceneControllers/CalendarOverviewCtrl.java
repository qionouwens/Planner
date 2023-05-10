package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import planner.client.serverUtils.CalendarServerUtils;
import planner.client.views.CalendarItemView;
import planner.commons.CalendarItem;
import planner.commons.helper.DateConversion;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarOverviewCtrl {
    private static CalendarOverviewCtrl INSTANCE;

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
    private AnchorPane calendarPane;

    private GregorianCalendar currentDate;
    private Stage stage;

    public CalendarOverviewCtrl() {
        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    public void setCalendarItems() {
        CalendarServerUtils calendarServerUtils = new CalendarServerUtils();
        int[] date = DateConversion.getDateArray(currentDate);
        List<CalendarItem> calendarItems = calendarServerUtils.getCalendarForWeek(date[0], date[1], date[2]);
        for (CalendarItem calendarItem : calendarItems) {
            calendarPane.getChildren().add(new CalendarItemView(calendarItem));
        }
    }

    public void initialise(GregorianCalendar calendar, Stage stage) {
        currentDate = calendar;
        setWeek(calendar);
        setCalendarItems();
        this.stage = stage;
    }

    public static CalendarOverviewCtrl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CalendarOverviewCtrl();
        }

        return INSTANCE;
    }

    public void setWeek(GregorianCalendar calendar) {
        GregorianCalendar startOfWeek = DateConversion.getFirstDay(calendar);
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

    public void nextWeek() {

    }

    public void prevWeek() {

    }

    public void close() {
        System.out.println("Hello");
        stage.close();
    }

    public void addButton() {

    }
}
