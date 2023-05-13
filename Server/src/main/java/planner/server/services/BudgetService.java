package planner.server.services;

import org.springframework.stereotype.Service;
import planner.commons.ResultCategory;
import planner.commons.Statement;
import planner.commons.StatementCategory;
import planner.database.controller.BudgetDBController;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetService {
    private final BudgetDBController budgetDBController;

    public BudgetService() {
        this.budgetDBController = new BudgetDBController();
    }

    public List<ResultCategory> getResultFromMonth(int year, int month) {
        return budgetDBController.getResultCategory(year, month);
    }

    public List<StatementCategory> getCategories() {
        return budgetDBController.getCategories();
    }

    public List<Statement> getRecent() {
        return budgetDBController.getMostRecent();
    }

    public List<ResultCategory> getAllForYear(int year) {
        List<ResultCategory> resultCategories = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            resultCategories.addAll(budgetDBController.getResultCategory(year, i));
            resultCategories.addAll(budgetDBController.getIncomeResult(year, i));
        }
        return resultCategories;
    }

    public void updateBudget(StatementCategory statementCategory) {
        budgetDBController.updateCategory(statementCategory);
    }

    public void updateStatement(Statement statement) {
        budgetDBController.updateStatement(statement);
    }

    public void addStatement(Statement statement) {
        budgetDBController.addStatement(statement);
    }

    public void addCategory(StatementCategory statementCategory) {
        budgetDBController.addCategory(statementCategory);
    }

    public void deleteStatement(Statement statement) {
        budgetDBController.deleteStatement(statement);
    }
}
