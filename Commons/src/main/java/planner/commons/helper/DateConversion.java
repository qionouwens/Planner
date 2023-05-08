package planner.commons.helper;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateConversion {
    public static GregorianCalendar getDate(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1 , day);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar;
    }

    public static int[] getDateArray(GregorianCalendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new int[]{year, month, day};
    }

    public static GregorianCalendar getFirstDay(GregorianCalendar calendar) {
        calendar.setWeekDate(calendar.getWeekYear(), calendar.get(Calendar.WEEK_OF_YEAR), Calendar.MONDAY);
        return calendar;
    }
}
