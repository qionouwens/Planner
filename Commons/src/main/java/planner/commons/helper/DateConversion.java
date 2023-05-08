package planner.commons.helper;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateConversion {
    public static GregorianCalendar getDate(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1 , day);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar;
    }

    public static GregorianCalendar getFirstDay(GregorianCalendar calendar) {
        calendar.setWeekDate(calendar.getWeekYear(), calendar.get(Calendar.WEEK_OF_YEAR), Calendar.MONDAY);
        return calendar;
    }
}
