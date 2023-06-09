package planner.client.sceneControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import planner.client.MainUICtrl;
import planner.commons.Todo;
import planner.commons.helper.DateConversion;

import java.util.List;

public class SeeTodoCtrl {
    @FXML
    private TableView<Todo> todoTable;
    @FXML
    private TableColumn<Todo, String> description;
    @FXML
    private TableColumn<Todo, String> date;
    private MainUICtrl mainUICtrl;
    private static SeeTodoCtrl INSTANCE;

    public SeeTodoCtrl() {
        INSTANCE = this;
    }

    public static SeeTodoCtrl getInstance() {
        return INSTANCE;
    }

    public void initialise(MainUICtrl mainUICtrl, List<Todo> todoList) {
        this.mainUICtrl = mainUICtrl;
        description.setCellValueFactory(todo -> new SimpleStringProperty(todo.getValue().getDescription()));
        date.setCellValueFactory(todo -> new SimpleStringProperty(DateConversion.getStringFromDate(todo.getValue().getDate())));
        todoTable.setItems(FXCollections.observableList(todoList));
    }

    public void close() {
        mainUICtrl.showHome();
    }
}
