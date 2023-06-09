package planner.commons.helper;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
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
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new int[]{year, month, day};
    }

    public static GregorianCalendar getDateFromString(String dateString) {
        Scanner scanner = new Scanner(dateString);
        scanner.useDelimiter("/");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        return getDate(year, month, day);
    }

    public static GregorianCalendar getGregorianCalendarClone(GregorianCalendar calendar) {
        int[] dates = getDateArray(calendar);
        return getDate(dates[0], dates[1], dates[2]);
    }

    public static String getStringFromDate(GregorianCalendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
                + calendar.get(Calendar.YEAR);
    }

    public static GregorianCalendar getFirstDay(GregorianCalendar calendar) {
        calendar.setWeekDate(calendar.getWeekYear(), calendar.get(Calendar.WEEK_OF_YEAR), Calendar.MONDAY);
        return calendar;
    }
}
