package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import planner.client.MainUICtrl;
import planner.client.serverUtils.ValueServerUtils;

import java.util.Scanner;

public class AddValueCtrl {
    @FXML
    private TextField value;
    private static AddValueCtrl INSTANCE;
    private MainUICtrl mainUICtrl;
    private String type;

    public AddValueCtrl() {
        INSTANCE = this;
    }

    public static AddValueCtrl getInstance() {
        return INSTANCE;
    }

    public void initialise(MainUICtrl mainUICtrl, String type) {
        this.mainUICtrl = mainUICtrl;
        this.type = type;
    }

    public int getWeight() {
        String weight = value.getText();
        Scanner scanner = new Scanner(weight);
        scanner.useDelimiter("\\.");
        return scanner.nextInt()*10 + scanner.nextInt();
    }

    public void save() {
        if (type.equals("sleep")) {
            ValueServerUtils.updateSleep(value.getText());
        }
        if (type.equals("weight")) {
            ValueServerUtils.updateWeight(getWeight());
        }
        close();
    }

    public void close() {
        mainUICtrl.showHome();
    }
}
