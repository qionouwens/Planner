package planner.server.controller;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CalendarItem>> getCalendarForWeek(@PathVariable("year") int year, @PathVariable("month") int month,
                                                           @PathVariable("day") int day) {
        GregorianCalendar calendar = DateConversion.getDate(year, month, day);
        return ResponseEntity.ok(calendarService.getByWeek(calendar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarItem> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(calendarService.getById(id));
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<CalendarItem> addCalendar(@RequestBody CalendarItem calendarItem) {
        return ResponseEntity.ok(calendarService.addCalendar(calendarItem));
    }
}