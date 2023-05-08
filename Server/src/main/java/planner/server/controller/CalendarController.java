package planner.server.controller;

import org.springframework.web.bind.annotation.*;
import planner.commons.CalendarItem;
import planner.commons.helper.DateConversion;
import planner.server.services.CalendarService;

import java.util.*;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarService calendarService = new CalendarService();

    @GetMapping("/{year}/{month}/{day}")
    public List<CalendarItem> getCalendarForWeek(@PathVariable("year") int year, @PathVariable("month") int month,
                                                 @PathVariable("day") int day) {
        GregorianCalendar calendar = DateConversion.getDate(year, month, day);
        return calendarService.getByWeek(calendar);
    }

    @PostMapping(path = {"", "/"})
    public CalendarItem addCalendar(@RequestBody CalendarItem calendarItem) {
        return calendarService.addCalendar(calendarItem);
    }
}