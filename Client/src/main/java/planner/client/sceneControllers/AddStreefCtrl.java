package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import planner.client.MainUICtrl;
import planner.client.serverUtils.TrainingServerUtils;
import planner.commons.Streef;

public class AddStreefCtrl {
    @FXML
    private TextField training;
    @FXML
    private TextField streef;
    private static AddStreefCtrl INSTANCE;
    private MainUICtrl mainUICtrl;

    public AddStreefCtrl() {
        INSTANCE = this;
    }

    public static AddStreefCtrl getInstance() {
        return INSTANCE;
    }

    public void initialise(MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
    }

    public void save() {
        Streef toBeAdded = new Streef(training.getText(), streef.getText());
        TrainingServerUtils.addStreef(toBeAdded);
        close();
    }

    public void close() {
        mainUICtrl.seeTrainingOverview();
    }
}
