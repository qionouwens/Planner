package planner.client.helper;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalendarOverview {
    public static int getX (GregorianCalendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_WEEK) - calendar.MONDAY;
        day = ((day % 7) + 7) % 7;
        return day*206;
    }

    public static double getY(String startTime) {
        int minutes = getMinutes(startTime);
        return map(0, 60*24, 0, 888, minutes);
    }

    public static double height(String endTime, String startTime) {
        int minutes = getMinutes(endTime) - getMinutes(startTime);
        return map(0, 60*24, 0, 888, minutes);
    }

    public static double map(int xfirst, int xlast, int yfirst, int ylast, int xvalue) {
        int xdiff = xlast - xfirst;
        int ydiff = ylast - yfirst;

        double percentage = (xvalue - xfirst) / ((double) xdiff);
        return (percentage * ydiff);
    }

    public static int getMinutes(String time) {
        Scanner scanner = new Scanner(time);
        scanner.useDelimiter(":");
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        return hours*60 + minutes;
    }
}
