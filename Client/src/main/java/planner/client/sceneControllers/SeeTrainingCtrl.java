package planner.client.sceneControllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import planner.client.MainUICtrl;
import planner.commons.Training;
import planner.commons.TrainingPart;

public class SeeTrainingCtrl {
    @FXML
    private TextField training;
    @FXML
    private TextField description;
    @FXML
    private TableView<TrainingPart> trainingParts;
    @FXML
    private TableColumn<TrainingPart, String> time;
    @FXML
    private TableColumn<TrainingPart, Integer> distance;
    private MainUICtrl mainUICtrl;
    private static SeeTrainingCtrl INSTANCE;
    private Training seeTraining;

    public SeeTrainingCtrl() {
        INSTANCE = this;
    }

    public static SeeTrainingCtrl getInstance() {
        return INSTANCE;
    }

    public void initialise(MainUICtrl mainUICtrl, Training training) {
        this.mainUICtrl = mainUICtrl;
        this.seeTraining = training;
        setFields();
    }

    public void initialiseTable() {
        time.setCellValueFactory(part -> new SimpleStringProperty(part.getValue().getTime()));
        distance.setCellValueFactory(part -> new SimpleIntegerProperty(part.getValue().getDistance()).asObject());
        trainingParts.setItems(FXCollections.observableList(seeTraining.getTrainingParts()));
    }

    public void setFields() {
        training.setText(seeTraining.getTrainingType());
        description.setText(seeTraining.getDescription());
        initialiseTable();
    }

    public void close() {
        mainUICtrl.seeTrainingOverview();
    }
}
