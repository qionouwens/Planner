package planner.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import planner.client.sceneControllers.CalendarOverviewCtrl;
import planner.commons.helper.DateConversion;

import java.io.IOException;
import java.time.LocalDateTime;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CalendarOverview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        CalendarOverviewCtrl calendarOverviewCtrl = CalendarOverviewCtrl.getInstance();
        LocalDateTime now = LocalDateTime.now();
        calendarOverviewCtrl.initialise(DateConversion.getDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth()), stage, new MainUICtrl(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}