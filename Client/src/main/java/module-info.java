module planner.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires planner.commons;

    opens planner.client to javafx.fxml;
    exports planner.client;
}