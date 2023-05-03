package planner.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import planner.commons.CalendarItem;

import java.util.*;

@RestController
public class CalendarController {

    @GetMapping("/calendar/")
    public List<CalendarItem> getCalendar() {
        System.out.println("Hello");
        List<CalendarItem> calendarItems = new ArrayList<>();
        calendarItems.add(new CalendarItem(0, "Title", new GregorianCalendar(2023, Calendar.MARCH, 1),
                "startTime", "endTime", "#ffffff", new ArrayList<>()));
        return calendarItems;
    }

}
