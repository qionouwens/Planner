package planner.client;

import planner.client.serverUtils.CalendarServerUtils;
import planner.commons.CalendarItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunClientCommands {
    public static void main(String[] args) {
        List<CalendarItem> calendarItems = new CalendarServerUtils().getCalendarForWeek(2023, 5, 30);
        for (CalendarItem calendar :
                calendarItems) {
            System.out.println(calendar.toString());
            System.out.println(calendar.toString());
        }
    }
}
