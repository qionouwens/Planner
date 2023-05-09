package planner.server;

import planner.database.controller.CalendarDBController;
import planner.database.controller.DateDBController;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RunServerCommands {
    public static void main(String[] args){
        DateDBController date = new DateDBController();
        CalendarDBController calendarDBController = new CalendarDBController();
        calendarDBController.add("testTitle", 2023, 6, 4, "9:15", "11:15", "#ffffff");

    }
}
