package planner.commons.helper;

import org.junit.jupiter.api.Test;
import planner.commons.CalendarItem;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ClassParserTest {

    @Test
    void convert() {
        String testString = "{\"id\":3,\"title\":\"testTitle\",\"date\":\"2023-05-01T00:00:00.000+00:00\",\"startTime\":\"9:15\",\"endTime\":\"11:15\",\"colour\":\"#ffffff\",\"todoList\":null}";
        GregorianCalendar calendar = DateConversion.getDate(2023, 5, 1);
        CalendarItem calendarItem = new CalendarItem(3, "testTitle", calendar, "9:15", "11:15", "#ffffff", null);
        assertEquals(calendarItem, ClassParser.parseCalendar(testString));
    }

    @Test
    void getInteger() {
        String testString = "{\"id\":3";
        assertEquals(3, ClassParser.getInteger(testString));
    }

    @Test
    void getString() {
        String testString = "\"title\":\"testTitle\"";
        assertEquals("testTitle", ClassParser.getString(testString));
    }

    @Test
    void getDate() {
        String testString = "\"date\":\"2023-05-01T00:00:00.000+00:00\"";
        GregorianCalendar calendar = DateConversion.getDate(2023, 5, 1);
        assertEquals(calendar, ClassParser.getDate(testString));
    }
}