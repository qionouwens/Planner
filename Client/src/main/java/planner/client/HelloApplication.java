package planner.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import planner.client.sceneControllers.HomeScreenCtrl;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        HomeScreenCtrl homeScreenCtrl = HomeScreenCtrl.getINSTANCE();
        homeScreenCtrl.initialise(stage, new MainUICtrl(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}