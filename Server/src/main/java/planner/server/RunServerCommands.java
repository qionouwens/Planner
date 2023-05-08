package planner.server;

import planner.database.controller.DateDBController;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RunServerCommands {
    public static void main(String[] args){
        DateDBController date = new DateDBController();
        ResultSet result = date.getResultSetDate(2023, 2, 3);
        try {
            System.out.println(result.next());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
