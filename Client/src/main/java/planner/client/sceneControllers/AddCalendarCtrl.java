package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import planner.client.MainUICtrl;
import planner.client.serverUtils.CalendarServerUtils;
import planner.commons.CalendarItem;
import planner.commons.helper.DateConversion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AddCalendarCtrl {
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
    private static AddCalendarCtrl INSTANCE;
    private Stage stage;
    private MainUICtrl mainUICtrl;

    public AddCalendarCtrl() {
        INSTANCE = this;
    }

    public static AddCalendarCtrl getInstance() {
        return INSTANCE;
    }

    public void initialise(Stage stage, MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
        this.stage = stage;
    }

    public void add() {
        GregorianCalendar calendar = DateConversion.getDateFromString(date.getText());
        String colour = colorPicker.getValue().toString();
        colour = colour.replace("0x", "#").substring(0, 7);
        CalendarItem calendarItem = new CalendarItem(0, title.getText(), calendar, startTime.getText(), endTime.getText(), colour, new ArrayList<>());
        CalendarServerUtils calendarServerUtils = new CalendarServerUtils();
        calendarServerUtils.addCalendar(calendarItem);
        try {
            close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void close() throws IOException {
        stage.close();
        mainUICtrl.showCalendarOverview();
    }
}
