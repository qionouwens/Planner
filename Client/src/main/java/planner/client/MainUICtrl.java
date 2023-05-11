package planner.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import planner.client.sceneControllers.AddCalendarCtrl;
import planner.client.sceneControllers.CalendarOverviewCtrl;
import planner.client.sceneControllers.SeeCalendarCtrl;
import planner.commons.CalendarItem;
import planner.commons.helper.DateConversion;

import java.io.IOException;
import java.time.LocalDateTime;

public class MainUICtrl {
    private final Stage stage;

    public MainUICtrl(Stage stage) {
        this.stage = stage;
    }

    public void showCalendarOverview() throws IOException {
        String calendarOverview = "CalendarOverview.fxml";
        showStage(calendarOverview, 1440, 1024);
        CalendarOverviewCtrl calendarOverviewCtrl = CalendarOverviewCtrl.getInstance();
        LocalDateTime now = LocalDateTime.now();
        calendarOverviewCtrl.initialise(DateConversion.getDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth()), stage, this);
    }

    public void showAddCalendar() throws IOException {
        String addCalendar = "AddCalendar.fxml";
        showStage(addCalendar, 450, 700);
        AddCalendarCtrl addCalendarCtrl = AddCalendarCtrl.getInstance();
        addCalendarCtrl.initialise(stage, this);
    }

    public void showCalendar(CalendarItem calendarItem) {
        String seeCalendar = "SeeCalendarItem.fxml";
        try {
            showStage(seeCalendar, 450, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SeeCalendarCtrl seeCalendarCtrl = SeeCalendarCtrl.getINSTANCE();
        seeCalendarCtrl.initialise(stage, this, calendarItem);
    }

    public void showStage(String fxmlScene, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlScene));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
