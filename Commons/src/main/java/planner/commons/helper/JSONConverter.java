package planner.commons.helper;

import planner.commons.CalendarItem;
import planner.commons.Statement;
import planner.commons.StatementCategory;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class JSONConverter {
    public static String convertCalendar(CalendarItem calendarItem) {
        String result = "{\"id\":" + calendarItem.getId() +
                ",\"title\":\"" + calendarItem.getTitle() + "\"" +
                ",\"date\":\"" + convertDate(calendarItem.getDate()) + "\"" +
                ",\"startTime\":\"" + calendarItem.getStartTime() + "\"" +
                ",\"endTime\":\"" + calendarItem.getEndTime() + "\"" +
                ",\"colour\":\"" + calendarItem.getColour() + "\"" +
                ",\"todoList\":null}";
        return result;
    }

    public static String convertStatement(Statement statement) {
        String isIncome = statement.isIncome() ? "true" : "false";
        String result = "{\"id\":" + statement.getId() + "," +
                "\"amount\":" + statement.getAmount() + "," +
                "\"category\":\"" + statement.getCategory() + "\"," +
                "\"date\":\"" + convertDate(statement.getDate()) + "\"," +
                "\"income\":" + isIncome + "}";
        return result;
    }

    public static String convertStatementCategory(StatementCategory statementCategory) {
        String result = "{\"id\":" + statementCategory.getId() +
                ",\"name\":\"" + statementCategory.getName() + "\"" +
                ",\"budget\":" + statementCategory.getBudget() + "}";
        return result;
    }

    static String convertDate(GregorianCalendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String monthString = month < 10 ? "0" + month : String.valueOf(month);
        String dayString = day < 10 ? "0" + day : String.valueOf(day);
        return year + "-" + monthString + "-" + dayString + "T00:00:00.000+00:00";
    }
}
