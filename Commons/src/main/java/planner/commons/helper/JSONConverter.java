package planner.commons.helper;

import planner.commons.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class JSONConverter {
    public static String convertCalendar(CalendarItem calendarItem) {
        return "{\"id\":" + calendarItem.getId() +
                ",\"title\":\"" + calendarItem.getTitle() + "\"" +
                ",\"date\":\"" + convertDate(calendarItem.getDate()) + "\"" +
                ",\"startTime\":\"" + calendarItem.getStartTime() + "\"" +
                ",\"endTime\":\"" + calendarItem.getEndTime() + "\"" +
                ",\"colour\":\"" + calendarItem.getColour() + "\"" +
                ",\"todoList\":null}";
    }

    public static String convertStatement(Statement statement) {
        String isIncome = statement.isIncome() ? "true" : "false";
        return "{\"id\":" + statement.getId() + "," +
                "\"amount\":" + statement.getAmount() + "," +
                "\"category\":\"" + statement.getCategory() + "\"," +
                "\"date\":\"" + convertDate(statement.getDate()) + "\"," +
                "\"income\":" + isIncome + "}";
    }

    public static String convertStatementCategory(StatementCategory statementCategory) {
        return "{\"id\":" + statementCategory.getId() +
                ",\"name\":\"" + statementCategory.getName() + "\"" +
                ",\"budget\":" + statementCategory.getBudget() + "}";
    }

    public static String convertUpdateDay(UpdateDay updateDay) {
        return "{\"calendar\":\"" + convertDate(updateDay.getCalendar()) + "\"" +
                ",\"categoryMap\":" + convertList(updateDay.getCategoryMap()) + "}";
    }

    public static String convertGroceryItem(GroceryItem groceryItem) {
        return "{\"id\":" + groceryItem.getId() +
                ",\"item\":\"" + groceryItem.getItem() + "\"" +
                ",\"quantity\":" + groceryItem.getQuantity() +
                ",\"priority\":\"" + groceryItem.getPriority() + "\"" +
                ",\"type\":\"" + groceryItem.getType() + "\"}";
    }

    static String convertDate(GregorianCalendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String monthString = month < 10 ? "0" + month : String.valueOf(month);
        String dayString = day < 10 ? "0" + day : String.valueOf(day);
        return year + "-" + monthString + "-" + dayString + "T00:00:00.000+00:00";
    }

    static String convertList(List<String> stringList) {
        if (stringList.size() == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String string : stringList) {
            stringBuilder.append("\"");
            stringBuilder.append(string);
            stringBuilder.append("\",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
