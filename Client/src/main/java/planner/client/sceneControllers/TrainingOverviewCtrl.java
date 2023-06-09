package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import planner.client.MainUICtrl;
import planner.client.serverUtils.TrainingServerUtils;
import planner.client.views.StreefView;
import planner.client.views.TrainingView;
import planner.commons.Streef;
import planner.commons.Training;

import java.util.List;

public class TrainingOverviewCtrl {
    @FXML
    private AnchorPane training;
    @FXML
    private AnchorPane streef;
    private MainUICtrl mainUICtrl;
    private static TrainingOverviewCtrl INSTANCE;

    public TrainingOverviewCtrl() {
        INSTANCE = this;
    }

    public static TrainingOverviewCtrl getInstance() {
        return INSTANCE;
    }

    public void initialise(MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
        setTrainingViews();
        setStreefViews();
    }

    public void setTrainingViews() {
        List<Training> trainings = TrainingServerUtils.getTraining();
        for (int i = 0; i < trainings.size(); i++) {
            TrainingView trainingView = new TrainingView(trainings.get(i), mainUICtrl);
            trainingView.setLayoutX(0);
            trainingView.setLayoutY(60*i);
            training.getChildren().add(trainingView);
        }
    }

    public void setStreefViews() {
        List<Streef> streefList = TrainingServerUtils.getStreef();
        for (int i = 0; i < streefList.size(); i++) {
            StreefView streefView = new StreefView(streefList.get(i), mainUICtrl);
            streefView.setLayoutX(0);
            streefView.setLayoutY(48*i);
            streef.getChildren().add(streefView);
        }
    }

    public void close() {
        mainUICtrl.showHome();
    }

    public void addTraining() {

    }

    public void addStreef() {
        mainUICtrl.addStreef();
    }
}
