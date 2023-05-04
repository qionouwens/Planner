package planner.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import planner.commons.CalendarItem;

import java.util.*;

@RestController
public class CalendarController {

    @GetMapping("/calendar/{year}/{month}/{day}")
    public List<CalendarItem> getCalendarForWeek(@PathVariable("year") int year, @PathVariable("month") int month,
                                                 @PathVariable("day") int day) {
        GregorianCalendar searchValue = new GregorianCalendar(year, month, day);
        while (searchValue.get(Calendar.DAY_OF_WEEK) > 2) {
            searchValue.add(Calendar.DAY_OF_WEEK, -1);
        }
        return null;
    }

}
