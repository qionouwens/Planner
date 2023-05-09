package planner.commons.helper;

import org.junit.jupiter.api.Test;
import planner.commons.CalendarItem;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class JSONConverterTest {

    @Test
    void convertCalendar() {
        GregorianCalendar calendar = DateConversion.getDate(2023, 5, 1);
        CalendarItem calendarItem = new CalendarItem(3, "testTitle", calendar, "9:15", "11:15", "#ffffff", null);
        String result = "{\"id\":3,\"title\":\"testTitle\",\"date\":\"2023-05-01T00:00:00.000+00:00\",\"startTime\":\"9:15\",\"endTime\":\"11:15\",\"colour\":\"#ffffff\",\"todoList\":null}";
        assertEquals(result, JSONConverter.convertCalendar(calendarItem));
    }

    @Test
    void convertDate() {
        String result = "2023-05-01T00:00:00.000+00:00";
        GregorianCalendar calendar = DateConversion.getDate(2023, 5, 1);
        assertEquals(result, JSONConverter.convertDate(calendar));
    }
}