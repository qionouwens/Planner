module planner.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires planner.commons;
    requires java.net.http;

    opens planner.client.sceneControllers to javafx.fxml;
    opens planner.client to javafx.fxml;
    exports planner.client;
    exports planner.client.sceneControllers;
}