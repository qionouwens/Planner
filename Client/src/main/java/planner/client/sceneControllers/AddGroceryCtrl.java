package planner.client.sceneControllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import planner.client.MainUICtrl;
import planner.client.serverUtils.GroceryServerUtils;
import planner.commons.GroceryItem;

import java.util.List;

public class AddGroceryCtrl {
    @FXML
    private TextArea productField;
    @FXML
    private TextArea quantityField;
    @FXML
    private ComboBox<String> priorityBox;

    private static AddGroceryCtrl INSTANCE;
    private MainUICtrl mainUICtrl;
    private String type;

    public AddGroceryCtrl() {
        INSTANCE = this;
    }

    public static AddGroceryCtrl getInstance() {
        return INSTANCE;
    }

    public void initialise(MainUICtrl mainUICtrl, String type) {
        this.mainUICtrl = mainUICtrl;
        this.type = type;
        List<String> priorities = GroceryServerUtils.getPriorities();
        priorityBox.setItems(FXCollections.observableList(priorities));
    }

    public void save() {
        String productString = productField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String priority = priorityBox.getValue();
        GroceryServerUtils.addGrocery(new GroceryItem(1, productString, quantity, priority, type));
        close();
    }

    public void close() {
        mainUICtrl.showHome();
    }
}
