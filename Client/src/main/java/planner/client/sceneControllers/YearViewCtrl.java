package planner.client.sceneControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import planner.client.MainUICtrl;
import planner.client.serverUtils.BudgetServerUtils;
import planner.commons.ResultCategory;
import planner.commons.YearResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YearViewCtrl {
    @FXML
    private TableView<YearResult> incomeTable;
    @FXML
    private TableView<YearResult> expenseTable;
    @FXML
    private TableColumn<YearResult, String> incat;
    @FXML
    private TableColumn<YearResult, String> excat;
    @FXML
    private TableColumn<YearResult, String> injan;
    @FXML
    private TableColumn<YearResult, String> infeb;
    @FXML
    private TableColumn<YearResult, String> inmar;
    @FXML
    private TableColumn<YearResult, String> inapr;
    @FXML
    private TableColumn<YearResult, String> inmay;
    @FXML
    private TableColumn<YearResult, String> injun;
    @FXML
    private TableColumn<YearResult, String> injul;
    @FXML
    private TableColumn<YearResult, String> inaug;
    @FXML
    private TableColumn<YearResult, String> insep;
    @FXML
    private TableColumn<YearResult, String> inokt;
    @FXML
    private TableColumn<YearResult, String> innov;
    @FXML
    private TableColumn<YearResult, String> indec;
    @FXML
    private TableColumn<YearResult, String> exjan;
    @FXML
    private TableColumn<YearResult, String> exfeb;
    @FXML
    private TableColumn<YearResult, String> exmar;
    @FXML
    private TableColumn<YearResult, String> exapr;
    @FXML
    private TableColumn<YearResult, String> exmay;
    @FXML
    private TableColumn<YearResult, String> exjun;
    @FXML
    private TableColumn<YearResult, String> exjul;
    @FXML
    private TableColumn<YearResult, String> exaug;
    @FXML
    private TableColumn<YearResult, String> exsep;
    @FXML
    private TableColumn<YearResult, String> exokt;
    @FXML
    private TableColumn<YearResult, String> exnov;
    @FXML
    private TableColumn<YearResult, String> exdec;
    private Map<String, int[]> incomeMap;
    private Map<String, int[]> expenseMap;
    private static YearViewCtrl INSTANCE;
    private MainUICtrl mainUICtrl;

    public YearViewCtrl() {
        INSTANCE = this;
    }

    public void initialise(MainUICtrl mainUICtrl) {
        this.mainUICtrl = mainUICtrl;
        incomeMap = new HashMap<>();
        expenseMap = new HashMap<>();
        getMaps();
        setUpTableColumn();
        setUpTable();
    }

    public static YearViewCtrl getInstance() {
        return INSTANCE;
    }

    public void getMaps() {
        BudgetServerUtils budgetServerUtils = new BudgetServerUtils();
        LocalDateTime now = LocalDateTime.now();
        List<ResultCategory> resultCategories = budgetServerUtils.getResultForYear(now.getYear());
        for (ResultCategory result : resultCategories) {
            String category = result.getName();
            if (result.isIncome()) {
                if (incomeMap.containsKey(category)) {
                    int[] oldArray = incomeMap.get(category);
                    oldArray[result.getMonth()-1] = result.getAmount();
                    incomeMap.put(category, oldArray);
                } else {
                    int[] newArray = new int[12];
                    newArray[result.getMonth()-1] = result.getAmount();
                    incomeMap.put(category, newArray);
                }
            } else {
                if (expenseMap.containsKey(category)) {
                    int[] oldArray = expenseMap.get(category);
                    oldArray[result.getMonth()-1] = result.getAmount();
                    expenseMap.put(category, oldArray);
                } else {
                    int[] newArray = new int[12];
                    newArray[result.getMonth()-1] = result.getAmount();
                    expenseMap.put(category, newArray);
                }
            }
        }
    }

    public void setUpTable() {
        List<YearResult> incomeResults = new ArrayList<>();
        for (String category : incomeMap.keySet()) {
            YearResult result = new YearResult(category, incomeMap.get(category));
            incomeResults.add(result);
        }
        List<YearResult> expenseResults = new ArrayList<>();
        for (String category : expenseMap.keySet()) {
            YearResult result = new YearResult(category, expenseMap.get(category));
            expenseResults.add(result);
        }
        ObservableList<YearResult> income = FXCollections.observableList(incomeResults);
        ObservableList<YearResult> expense = FXCollections.observableList(expenseResults);
        incomeTable.setItems(income);
        expenseTable.setItems(expense);
    }

    public void setUpTableColumn() {
        incat.setCellValueFactory(result -> new SimpleStringProperty(result.getValue().getCategory()));
        injan.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(0)/100) + "," + (result.getValue().getMonth(0) % 100)));
        infeb.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(1)/100) + "," + (result.getValue().getMonth(1) % 100)));
        inmar.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(2)/100) + "," + (result.getValue().getMonth(2) % 100)));
        inapr.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(3)/100) + "," + (result.getValue().getMonth(3) % 100)));
        inmay.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(4)/100) + "," + (result.getValue().getMonth(4) % 100)));
        injun.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(5)/100) + "," + (result.getValue().getMonth(5) % 100)));
        injul.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(6)/100) + "," + (result.getValue().getMonth(6) % 100)));
        inaug.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(7)/100) + "," + (result.getValue().getMonth(7) % 100)));
        insep.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(8)/100) + "," + (result.getValue().getMonth(8) % 100)));
        inokt.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(9)/100) + "," + (result.getValue().getMonth(9) % 100)));
        innov.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(10)/100) + "," + (result.getValue().getMonth(10) % 100)));
        indec.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(11)/100) + "," + (result.getValue().getMonth(11) % 100)));
        excat.setCellValueFactory(result -> new SimpleStringProperty(result.getValue().getCategory()));
        exjan.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(0)/100) + "," + (result.getValue().getMonth(0) % 100)));
        exfeb.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(1)/100) + "," + (result.getValue().getMonth(1) % 100)));
        exmar.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(2)/100) + "," + (result.getValue().getMonth(2) % 100)));
        exapr.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(3)/100) + "," + (result.getValue().getMonth(3) % 100)));
        exmay.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(4)/100) + "," + (result.getValue().getMonth(4) % 100)));
        exjun.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(5)/100) + "," + (result.getValue().getMonth(5) % 100)));
        exjul.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(6)/100) + "," + (result.getValue().getMonth(6) % 100)));
        exaug.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(7)/100) + "," + (result.getValue().getMonth(7) % 100)));
        exsep.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(8)/100) + "," + (result.getValue().getMonth(8) % 100)));
        exokt.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(9)/100) + "," + (result.getValue().getMonth(9) % 100)));
        exnov.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(10)/100) + "," + (result.getValue().getMonth(10) % 100)));
        exdec.setCellValueFactory(result -> new SimpleStringProperty((result.getValue().getMonth(11)/100) + "," + (result.getValue().getMonth(11) % 100)));
    }

    public void close() {
        mainUICtrl.showHome();
    }
}
