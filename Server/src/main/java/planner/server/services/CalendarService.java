package planner.server.services;

import org.springframework.stereotype.Service;
import planner.commons.CalendarItem;
import planner.commons.helper.DateConversion;
import planner.database.controller.CalendarDBController;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class CalendarService {
    private final CalendarDBController calendarDBController;

    public CalendarService() {
        calendarDBController = new CalendarDBController();
    }
    public List<CalendarItem> getByWeek(GregorianCalendar calendar) {
        GregorianCalendar startOfWeek = DateConversion.getFirstDay(calendar);
        if (startOfWeek.getActualMaximum(Calendar.DAY_OF_MONTH) - startOfWeek.get(Calendar.DAY_OF_MONTH) < 6) {
            return calendarDBController.getMiddleWeek(startOfWeek);
        }
        return calendarDBController.getEndOfMonth(startOfWeek);
    }

    public CalendarItem addCalendar(CalendarItem calendarItem) {
        int[] date = DateConversion.getDateArray(calendarItem.getDate());
        calendarDBController.add(calendarItem.getTitle(), date[0], date[1], date[2], calendarItem.getStartTime(),
                calendarItem.getEndTime(), calendarItem.getColour());
        int cal_id = calendarDBController.getId(date[0], date[1], date[2], calendarItem.getStartTime());
        calendarItem.setId(cal_id);
        return calendarItem;
    }
}
