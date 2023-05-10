package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import planner.client.MainUICtrl;
import planner.client.serverUtils.CalendarServerUtils;
import planner.client.views.CalendarItemView;
import planner.commons.CalendarItem;
import planner.commons.helper.DateConversion;

import java.io.IOException;
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
    @FXML
    private GridPane gridPane;

    private GregorianCalendar currentDate;
    private Stage stage;
    private MainUICtrl mainUICtrl;

    public CalendarOverviewCtrl() {
        INSTANCE = this;
    }

    public void setCalendarItems() {
        calendarPane.getChildren().clear();
        CalendarServerUtils calendarServerUtils = new CalendarServerUtils();
        int[] date = DateConversion.getDateArray(currentDate);
        List<CalendarItem> calendarItems = calendarServerUtils.getCalendarForWeek(date[0], date[1], date[2]);
        for (CalendarItem calendarItem : calendarItems) {
            if (calendarItem != null) {
                calendarPane.getChildren().add(new CalendarItemView(calendarItem));
            }

        }
    }

    public void initialise(GregorianCalendar calendar, Stage stage, MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
        currentDate = calendar;
        setWeek(calendar);
        setCalendarItems();
        setAllTimes();
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

    public void setAllTimes() {
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 24; j++) {
                Label newLabel = new Label();
                newLabel.setPrefSize(206, 37);
                newLabel.setText(" " + j + ":00");
                newLabel.setAlignment(Pos.TOP_LEFT);
                gridPane.add(newLabel, i, j+1);
            }
        }
    }

    private String turnIntoString(GregorianCalendar c) {
        return c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1);
    }

    public void nextWeek() {
        currentDate.add(Calendar.DAY_OF_MONTH, 7);
        setWeek(currentDate);
        setCalendarItems();
    }

    public void prevWeek() {
        currentDate.add(Calendar.DAY_OF_MONTH, -7);
        setWeek(currentDate);
        setCalendarItems();
    }

    public void close() {
        System.out.println("Hello");
        stage.close();
    }

    public void addButton() throws IOException {
        mainUICtrl.showAddCalendar();
    }
}
