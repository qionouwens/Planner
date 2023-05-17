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
        StatementCategory stmt = new StatementCategory(0, "Fun", 500);
        budgetDBController.addCategory(stmt);
        stmt = new StatementCategory(0, "Clothing", 500);
        budgetDBController.addCategory(stmt);
        stmt = new StatementCategory(0, "Groceries", 500);
        budgetDBController.addCategory(stmt);

        System.out.println("h");
    }
}
