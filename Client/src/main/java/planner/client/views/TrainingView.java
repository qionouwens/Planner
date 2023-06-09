package planner.client.views;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import planner.client.MainUICtrl;
import planner.commons.Training;

public class TrainingView extends AnchorPane {
    private final Training training;
    private final MainUICtrl mainUICtrl;

    public TrainingView(Training training, MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
        this.training = training;
        createView();
    }

    public void createView() {
        this.setPrefSize(662, 60);

        Label type = new Label(training.getTrainingType());
        type.setPrefSize(338, 60);
        type.setStyle("-fx-border-color: black");
        type.setFont(new Font(36));
        type.setLayoutX(0);
        type.setLayoutY(0);
        type.setAlignment(Pos.CENTER);

        Label description = new Label(training.getDescription());
        description.setPrefSize(323, 60);
        description.setStyle("-fx-border-color: black");
        description.setFont(new Font(36));
        description.setLayoutX(338);
        description.setLayoutY(0);
        description.setAlignment(Pos.CENTER);

        this.getChildren().add(type);
        this.getChildren().add(description);
    }
}
