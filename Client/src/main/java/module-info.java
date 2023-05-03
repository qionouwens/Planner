module planner.client {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens planner.client to javafx.fxml;
    exports planner.client;
}