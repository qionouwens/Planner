package planner.server;

import planner.commons.ResultCategory;
import planner.commons.Statement;
import planner.commons.StatementCategory;
import planner.commons.helper.DateConversion;
import planner.database.controller.BudgetDBController;
import planner.database.controller.CalendarDBController;
import planner.database.controller.DateDBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

public class RunServerCommands {
    public static void main(String[] args){
        BudgetDBController budgetDBController = new BudgetDBController();
        List<StatementCategory> categories = budgetDBController.getCategories();
        List<ResultCategory> categories1 = budgetDBController.getResultCategory(2023, 4);

        System.out.println("h");
    }
}
