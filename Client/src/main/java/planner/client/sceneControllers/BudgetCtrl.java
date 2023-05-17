package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import planner.client.MainUICtrl;
import planner.client.serverUtils.BudgetServerUtils;
import planner.commons.StatementCategory;

import java.util.List;
import java.util.Scanner;

public class BudgetCtrl {
    @FXML
    private ComboBox<String> categories;
    @FXML
    private TextField category;
    @FXML
    private TextField amount;
    @FXML
    private Button addNew;
    private static BudgetCtrl INSTANCE;
    private MainUICtrl mainUICtrl;
    private boolean isEdit = false;

    public BudgetCtrl() {
        INSTANCE = this;
    }

    public void Initialise(MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
        BudgetServerUtils budgetServerUtils = new BudgetServerUtils();
        List<StatementCategory> categoryList = budgetServerUtils.getCategories();
        for (StatementCategory stmt : categoryList) {
            categories.getItems().add(stmt.getName());
        }
    }

    public static BudgetCtrl getInstance() {
        return INSTANCE;
    }

    public void addNew() {
        categories.setVisible(false);
        category.setVisible(true);
        addNew.setVisible(false);
        this.isEdit = true;
    }

    public void save() {
        BudgetServerUtils budgetServerUtils = new BudgetServerUtils();
        List<StatementCategory> statementCategories = budgetServerUtils.getCategories();
        String amountString = this.amount.getText();
        Scanner scanner = new Scanner(amountString);
        scanner.useDelimiter("[,.]");
        int amountAdd = scanner.nextInt();
        amountAdd = amountAdd*100 + scanner.nextInt();
        if (isEdit) {
            StatementCategory statementCategory = new StatementCategory(0, category.getText(), amountAdd);
            budgetServerUtils.addCategory(statementCategory);
            return;
        }
        String category = categories.getValue();
        for (StatementCategory stmt : statementCategories) {
            if (stmt.getName().equals(category)) {
                budgetServerUtils.updateCategory(new StatementCategory(stmt.getId(), stmt.getName(), amountAdd));
            }
        }
        close();
    }

    public void close() {
        mainUICtrl.showHome();
    }
}
