package planner.server;

import planner.database.controller.CalendarDBController;
import planner.database.controller.DateDBController;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RunServerCommands {
    public static void main(String[] args){
        DateDBController date = new DateDBController();
        CalendarDBController calendarDBController = new CalendarDBController();
        calendarDBController.add("testTitle", 2023, 5, 14, "13:15", "15:15", "#ff0000");

    }
}
