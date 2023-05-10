package planner.commons.helper;

import planner.commons.CalendarItem;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class ClassParser {
    public static List<CalendarItem> parseCalendarList(String calendarString) {
        List<CalendarItem> calendarItems = new ArrayList<>();
        Scanner scanner = new Scanner(calendarString);
        scanner.useDelimiter("},");
        while (scanner.hasNext()) {
            calendarItems.add(parseCalendar(scanner.next()));
        }
        return calendarItems;
    }
    public static CalendarItem parseCalendar(String calendarString) {
        Scanner scanner = new Scanner(calendarString);
        if (calendarString.equals("[null]")) {
            return null;
        }
        scanner.useDelimiter(",");
        String id = scanner.next();
        String title = scanner.next();
        String date = scanner.next();
        String startTime = scanner.next();
        String endTime = scanner.next();
        String colour = scanner.next();
        return new CalendarItem(getInteger(id), getString(title), getDate(date), getString(startTime),
                getString(endTime), getString(colour), null);
    }

    static int getInteger(String idString) {
        Scanner scanner = new Scanner(idString);
        scanner.useDelimiter(":");
        scanner.next();
        return scanner.nextInt();
    }

    static String getString(String titleString) {
        Scanner scanner = new Scanner(titleString);
        scanner.useDelimiter("\":\"");
        scanner.next();
        String title = scanner.next();
        return title.replaceAll("\"", "");
    }

    static GregorianCalendar getDate(String dateString) {
        String year = dateString.substring(8, 12);
        String month = dateString.substring(13, 15);
        String day = dateString.substring(16, 18);
        return DateConversion.getDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }
}
