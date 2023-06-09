package planner.client.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import planner.client.MainUICtrl;
import planner.client.serverUtils.GroceryServerUtils;
import planner.commons.GroceryItem;

public class GroceryView extends AnchorPane {
    private final GroceryItem groceryItem;
    private final MainUICtrl mainUICtrl;

    public GroceryView(GroceryItem groceryItem, MainUICtrl mainUICtrl) {
        super();
        this.groceryItem = groceryItem;
        this.mainUICtrl = mainUICtrl;
        createView();
    }

    public void createView() {
        this.setPrefSize(358, 24);

        Label product = new Label(groceryItem.getItem());
        product.setPrefSize(175, 24);
        product.setAlignment(Pos.CENTER);
        product.setStyle("-fx-border-color: black");
        product.setLayoutX(0);
        product.setLayoutY(0);

        Label quantity = new Label(String.valueOf(groceryItem.getQuantity()));
        quantity.setPrefSize(35, 24);
        quantity.setAlignment(Pos.CENTER);
        quantity.setStyle("-fx-border-color: black");
        quantity.setLayoutX(174);
        quantity.setLayoutY(0);

        Label priority = new Label(groceryItem.getPriority());
        priority.setPrefSize(113, 24);
        priority.setAlignment(Pos.CENTER);
        priority.setStyle("-fx-border-color: black");
        priority.setLayoutX(208);
        priority.setLayoutY(0);

        Button deleteButton = new Button("x");
        deleteButton.setFont(new Font(10));
        deleteButton.setPrefSize(10, 20);
        deleteButton.setLayoutX(329);
        deleteButton.setLayoutY(2);
        deleteButton.setOnAction(event -> {
            GroceryServerUtils.deleteGrocery(groceryItem.getId());
            mainUICtrl.showHome();
        });

        this.getChildren().addAll(product, quantity, priority, deleteButton);
    }
}
