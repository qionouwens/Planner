package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import planner.client.MainUICtrl;
import planner.commons.helper.DateConversion;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeScreenCtrl {
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
    private static HomeScreenCtrl INSTANCE;
    private MainUICtrl mainUICtrl;
    private GregorianCalendar today;
    private GregorianCalendar currentDate;
    private Stage stage;

    public HomeScreenCtrl() {
        INSTANCE = this;
    }

    public void initialise(Stage stage, MainUICtrl mainUICtrl) {
        this.stage = stage;
        this.mainUICtrl = mainUICtrl;
        LocalDateTime now = LocalDateTime.now();
        today = DateConversion.getDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        currentDate = DateConversion.getDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        setWeek();
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

}
