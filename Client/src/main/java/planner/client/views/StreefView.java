package planner.client.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import planner.client.MainUICtrl;
import planner.commons.Streef;

public class StreefView extends AnchorPane {
    private final Streef streef;
    private final MainUICtrl mainUICtrl;

    public StreefView(Streef streef, MainUICtrl mainUICtrl) {
        this.streef = streef;
        this.mainUICtrl = mainUICtrl;
        createView();
    }

    public void createView() {
        this.setPrefSize(469, 48);

        Label training = new Label(streef.getTraining());
        training.setFont(new Font(24));
        training.setPrefSize(200, 48);
        training.setAlignment(Pos.CENTER);
        training.setLayoutX(0);
        training.setLayoutY(0);

        Label streefLabel = new Label(streef.getStreef());
        streefLabel.setFont(new Font(24));
        streefLabel.setPrefSize(120, 48);
        streefLabel.setAlignment(Pos.CENTER);
        streefLabel.setLayoutX(200);
        streefLabel.setLayoutY(0);

        Button update = new Button("Update");
        update.setPrefSize(142, 25);
        update.setLayoutX(324);
        update.setLayoutY(12);
        update.setStyle("-fx-background-radius: 20px");

        this.getChildren().addAll(training, streefLabel, update);
    }
}
