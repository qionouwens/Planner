package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import planner.client.MainUICtrl;
import planner.client.serverUtils.BudgetServerUtils;
import planner.commons.Statement;
import planner.commons.StatementCategory;
import planner.commons.helper.DateConversion;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class AddExpenseCtrl {
    @FXML
    private RadioButton income;
    @FXML
    private RadioButton expense;
    @FXML
    private ComboBox<String> categories;
    @FXML
    private TextField amount;
    @FXML
    private TextField date;
    private MainUICtrl mainUICtrl;
    private static AddExpenseCtrl INSTANCE;
    private Statement statement;
    private boolean isEdit;

    public AddExpenseCtrl() {
        INSTANCE = this;
    }

    public static AddExpenseCtrl getInstance() {
        return INSTANCE;
    }

    public void initialise(MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
        isEdit = false;
        setCategories();
    }

    public void initialise(MainUICtrl mainUICtrl, Statement statement) {
        this.mainUICtrl = mainUICtrl;
        this.statement = statement;
        isEdit = true;
        setCategories();
        showCurrent();
    }

    public void setCategories() {
        BudgetServerUtils budgetServerUtils = new BudgetServerUtils();
        List<StatementCategory> categoryList = budgetServerUtils.getCategories();
        for (StatementCategory stmt : categoryList) {
            categories.getItems().add(stmt.getName());
        }
    }

    public void showCurrent() {
        if (statement.isIncome())  {
            income.setSelected(true);
            expense.setSelected(false);
        } else {
            income.setSelected(false);
            expense.setSelected(true);
        }
        categories.setValue(statement.getCategory());
        amount.setText((statement.getAmount()/100) + "," + (statement.getAmount()%100));
        date.setText(DateConversion.getStringFromDate(statement.getDate()));
    }

    public void save() {
        BudgetServerUtils budgetServerUtils = new BudgetServerUtils();
        // get income
        boolean isIncome = income.isSelected();
        // get category
        String category = categories.getValue();
        // get amount
        String amountString = this.amount.getText();
        Scanner scanner = new Scanner(amountString);
        scanner.useDelimiter("[,.]");
        int amountAdd = scanner.nextInt();
        amountAdd = amountAdd*100 + scanner.nextInt();
        // get date
        GregorianCalendar calendar = DateConversion.getDateFromString(date.getText());
        if (isEdit){
            Statement added = new Statement(statement.getId(), isIncome, amountAdd, category, calendar);
            budgetServerUtils.updateStatement(added);
            mainUICtrl.showExpenses();
            return;
        }
        Statement added = new Statement(0, isIncome, amountAdd, category, calendar);
        budgetServerUtils.addStatement(added);
        mainUICtrl.showExpenses();
    }

    public void close() {
        mainUICtrl.showExpenses();
    }
}
