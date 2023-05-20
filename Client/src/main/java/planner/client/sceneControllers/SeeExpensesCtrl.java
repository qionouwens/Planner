package planner.client.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import planner.client.MainUICtrl;
import planner.client.serverUtils.BudgetServerUtils;
import planner.commons.Statement;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SeeExpensesCtrl {
    @FXML
    private GridPane grid;
    private static SeeExpensesCtrl INSTANCE;
    private MainUICtrl mainUICtrl;
    private List<Statement> statements;
    public SeeExpensesCtrl() {
        INSTANCE = this;
    }

    public void initialise(MainUICtrl mainUICtrl) {
        BudgetServerUtils budgetServerUtils = new BudgetServerUtils();
        statements = budgetServerUtils.getRecent();
        setExpenses();
        this.mainUICtrl = mainUICtrl;
    }

    public static SeeExpensesCtrl getINSTANCE() {
        return INSTANCE;
    }

    public void setExpenses() {
        for (int i = 0; i < statements.size(); i++) {
            GregorianCalendar calendar = statements.get(i).getDate();
            Label dateLabel = new Label(calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                    (calendar.get(Calendar.MONTH) + 1) + "/" +
                    calendar.get(Calendar.YEAR));
            String inOut = statements.get(i).isIncome() ? "In" : "Out";
            Label inOutLabel = new Label(inOut);
            Label categorieLabel = new Label(statements.get(i).getCategory());
            int euros = statements.get(i).getAmount() / 100;
            int cents = statements.get(i).getAmount() % 100;
            String centString = cents < 10 ? "0" + cents : String.valueOf(cents);
            Label amountLabel = new Label(euros + "," + centString);
            Button editButton = new Button("edit");
            Button deleteButton = new Button("x");
            grid.add(dateLabel, 0, i+1);
            grid.add(inOutLabel, 1, i+1);
            grid.add(categorieLabel, 2, i+1);
            grid.add(amountLabel, 3, i+1);
            grid.add(editButton, 4, i+1);
            grid.add(deleteButton, 5, i+1);
            int finalI = i;
            editButton.setOnAction(event -> edit(finalI));
            deleteButton.setOnAction(event -> delete(finalI));
        }
    }

    public void edit(int i) {
        mainUICtrl.showEditStatement(statements.get(i));
    }

    public void delete(int i) {
        BudgetServerUtils budgetServerUtils = new BudgetServerUtils();
        budgetServerUtils.deleteStatement(statements.get(i).getId());
        initialise(mainUICtrl);
    }

    public void add() {
        mainUICtrl.showAddStatement();
    }

    public void close() {
        mainUICtrl.showHome();
    }
}
