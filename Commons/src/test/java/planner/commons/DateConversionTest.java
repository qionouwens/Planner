package planner.commons;

import org.junit.jupiter.api.Test;
import planner.commons.helper.DateConversion;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class DateConversionTest {

    @Test
    void getDate() {
        GregorianCalendar calendar = DateConversion.getDate(2023, 5, 7);
        assertEquals(Calendar.SUNDAY, calendar.get(Calendar.DAY_OF_WEEK));
        assertEquals(Calendar.MAY, calendar.get(Calendar.MONTH));
    }

    @Test
    void getFirstDay() {
        GregorianCalendar calendar = DateConversion.getDate(2023, 5, 7);
        DateConversion.getFirstDay(calendar);
        assertEquals(Calendar.MONDAY, calendar.get(Calendar.DAY_OF_WEEK));
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.MAY, calendar.get(Calendar.MONTH));
    }

    @Test
    void getFirstDay2() {
        GregorianCalendar calendar = DateConversion.getDate(2023, 6, 4);
        DateConversion.getFirstDay(calendar);
        assertEquals(Calendar.MONDAY, calendar.get(Calendar.DAY_OF_WEEK));
        assertEquals(29, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.MAY, calendar.get(Calendar.MONTH));
    }
}