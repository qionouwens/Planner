package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import planner.client.MainUICtrl;
import planner.client.serverUtils.TodoServerUtils;
import planner.commons.Todo;
import planner.commons.helper.DateConversion;

public class AddTodoCtrl {
    @FXML
    private TextField description;
    @FXML
    private TextField date;
    private static AddTodoCtrl INSTANCE;
    private MainUICtrl mainUICtrl;

    public AddTodoCtrl() {
        INSTANCE = this;
    }

    public void initialise(MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
    }

    public static AddTodoCtrl getInstance() {
        return INSTANCE;
    }

    public void save() {
        String descriptionString = description.getText();
        String dueDate = date.getText();
        TodoServerUtils.addTodo(new Todo(1, descriptionString, DateConversion.getDateFromString(dueDate)));
        close();
    }

    public void close() {
        mainUICtrl.showHome();
    }
}
