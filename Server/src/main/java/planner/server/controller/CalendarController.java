package planner.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import planner.commons.CalendarItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CalendarController {

    @GetMapping("/calendar/")
    public List<CalendarItem> getCalendar() {
        System.out.println("Hello");
        List<CalendarItem> calendarItems = new ArrayList<>();
        calendarItems.add(new CalendarItem(0, "Title", new Date(2023, 2, 1),
                "startTime", "endTime", "#ffffff", new ArrayList<>()));
        return calendarItems;
    }

}
