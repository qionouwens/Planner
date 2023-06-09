package planner.commons.helper;

import planner.commons.*;

import java.util.ArrayList;
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
                ",\"categoryMap\":" + convertStringList(updateDay.getCategoryMap()) + "}";
    }

    public static String convertGroceryItem(GroceryItem groceryItem) {
        return "{\"id\":" + groceryItem.getId() +
                ",\"item\":\"" + groceryItem.getItem() + "\"" +
                ",\"quantity\":" + groceryItem.getQuantity() +
                ",\"priority\":\"" + groceryItem.getPriority() + "\"" +
                ",\"type\":\"" + groceryItem.getType() + "\"}";
    }

    public static String convertInventoryItem(InventoryItem inventoryItem) {
        return "{\"id\":" + inventoryItem.getId() +
                ",\"item\":\"" + inventoryItem.getItem() + "\"" +
                ",\"quantity\":" + inventoryItem.getQuantity() +
                ",\"location\":\"" + inventoryItem.getLocation() + "\"}";
    }

    public static String convertTodo(Todo todo) {
        return "{\"id\":" + todo.getId() +
                ",\"description\":\"" + todo.getDescription() + "\"" +
                ",\"date\":\"" + convertDate(todo.getDate()) + "\"}";
    }

    public static String convertTrainingPart(TrainingPart trainingPart) {
        return "{\"id\":" + trainingPart.getId() +
                ",\"distance\":" + trainingPart.getDistance() +
                ",\"time\":\"" + trainingPart.getTime() + "\"}";
    }

    public static String convertTraining(Training training) {
        List<String> trainingParts = new ArrayList<>();
        for (TrainingPart trainingPart : training.getTrainingParts()) {
            trainingParts.add(convertTrainingPart(trainingPart));
        }
        return "{\"id\":" + training.getId() +
                ",\"trainingType\":\"" + training.getTrainingType() + "\"" +
                ",\"description\":\"" + training.getDescription() + "\"" +
                ",\"calendar\":\"" + convertDate(training.getCalendar()) + "\"" +
                ",\"trainingParts\":" + convertList(trainingParts) + "}";
    }

    public static String convertStreef(Streef streef) {
        return "{\"training\":\"" + streef.getTraining() + "\"," +
                "\"streef\":\"" + streef.getStreef() + "\"}";
    }

    static String convertDate(GregorianCalendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String monthString = month < 10 ? "0" + month : String.valueOf(month);
        String dayString = day < 10 ? "0" + day : String.valueOf(day);
        return year + "-" + monthString + "-" + dayString + "T00:00:00.000+00:00";
    }

    static String convertStringList(List<String> stringList) {
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

    static String convertList(List<String> stringList) {
        if (stringList.size() == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String string : stringList) {
            stringBuilder.append(string);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
