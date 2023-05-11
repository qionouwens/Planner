package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import planner.client.MainUICtrl;
import planner.client.serverUtils.CalendarServerUtils;
import planner.commons.CalendarItem;
import planner.commons.helper.DateConversion;

import java.util.GregorianCalendar;

public class SeeCalendarCtrl {
    @FXML
    private TextField title;
    @FXML
    private TextField date;
    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button saveButton;
    private Stage stage;
    private static SeeCalendarCtrl INSTANCE;
    private MainUICtrl mainUICtrl;
    private CalendarItem calendarItem;

    public SeeCalendarCtrl() {
        INSTANCE = this;
    }

    public static SeeCalendarCtrl getINSTANCE() {
        return INSTANCE;
    }

    public void initialise(Stage stage, MainUICtrl mainUICtrl, CalendarItem calendarItem) {
        this.stage = stage;
        this.mainUICtrl = mainUICtrl;
        this.calendarItem = calendarItem;
        setFields();
    }

    public void setFields() {
        title.setText(calendarItem.getTitle());
        int[] dateArray = DateConversion.getDateArray(calendarItem.getDate());
        date.setText(dateArray[2] + "/" + dateArray[1] + "/" + dateArray[0]);
        startTime.setText(calendarItem.getStartTime());
        endTime.setText(calendarItem.getEndTime());
        colorPicker.setValue(Color.web(calendarItem.getColour()));
    }

    public void edit() {
        saveButton.setVisible(true);
        title.setEditable(true);
        date.setEditable(true);
        startTime.setEditable(true);
        endTime.setEditable(true);
        colorPicker.setEditable(true);
    }

    public void delete() {
        CalendarServerUtils calendarServerUtils = new CalendarServerUtils();
        calendarServerUtils.deleteCalendar(calendarItem.getId());
        close();
    }

    public void close() {
        stage.close();
        mainUICtrl.showCalendarOverview();
    }

    public void save() {
        GregorianCalendar calendar = DateConversion.getDateFromString(date.getText());
        String colour = colorPicker.getValue().toString();
        colour = colour.replace("0x", "#").substring(0, 7);
        CalendarItem updatedCalendar = new CalendarItem(calendarItem.getId(), title.getText(), calendar, startTime.getText(), endTime.getText(), colour, null);
        CalendarServerUtils calendarServerUtils = new CalendarServerUtils();
        calendarServerUtils.updateCalendar(updatedCalendar);
        close();
    }
}
