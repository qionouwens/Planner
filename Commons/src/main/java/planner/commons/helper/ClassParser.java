package planner.commons.helper;

import planner.commons.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class ClassParser {
    public static List<CalendarItem> parseCalendarList(String calendarString) {
        if (calendarString.equals("[]")) {
            return new ArrayList<>();
        }
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

    public static Statement getStatement(String statement) {
        Scanner scanner = new Scanner(statement);
        if (statement.equals("[null]")) {
            return null;
        }
        scanner.useDelimiter(",");
        int id = getInteger(scanner.next());
        int amount = getInteger(scanner.next());
        String category = getString(scanner.next());
        GregorianCalendar date = getDate(scanner.next());
        boolean isIncome = getBoolean(scanner.next());
        return new Statement(id, isIncome, amount, category, date);
    }

    public static List<Statement> getStatementList(String statementList) {
        List<Statement> statements = new ArrayList<>();
        if (statementList.equals("[]")) {
            return statements;
        }
        Scanner scanner = new Scanner(statementList);
        scanner.useDelimiter("},");
        while (scanner.hasNext()) {
            statements.add(getStatement(scanner.next()));
        }
        return statements;
    }

    public static StatementCategory getCategory(String category) {
        if (category.equals("[null]")) {
            return null;
        }
        category = category.replaceAll("[}\\]]", "");
        Scanner scanner = new Scanner(category);
        scanner.useDelimiter(",");
        int id = getInteger(scanner.next());
        String name = getString(scanner.next());
        int budget = getInteger(scanner.next());
        return new StatementCategory(id, name, budget);
    }

    public static List<StatementCategory> getCategoryList(String categoryList) {
        List<StatementCategory> categories = new ArrayList<>();
        if (categoryList.equals("[]")) {
            return categories;
        }
        Scanner scanner = new Scanner(categoryList);
        scanner.useDelimiter("},");
        while (scanner.hasNext()) {
            categories.add(getCategory(scanner.next()));
        }
        return categories;
    }

    public static List<CleaningTask> getCleaningTaskList(String cleaningTaskList) {
        List<CleaningTask> cleaningTasks = new ArrayList<>();
        if (cleaningTaskList.equals("[]")) {
            return cleaningTasks;
        }
        Scanner scanner = new Scanner(cleaningTaskList);
        scanner.useDelimiter("},");
        while (scanner.hasNext()) {
            cleaningTasks.add(getCleaningTask(scanner.next()));
        }
        return cleaningTasks;
    }

    public static CleaningTask getCleaningTask(String cleaningTask) {
        Scanner scanner = new Scanner(cleaningTask);
        scanner.useDelimiter(",");
        String name = getString(scanner.next());
        int frequency = getInteger(scanner.next());
        GregorianCalendar calendar = getDate(scanner.next());
        return new CleaningTask(name, frequency, calendar);
    }

    public static ResultCategory getResult(String result) {
        Scanner scanner = new Scanner(result);
        scanner.useDelimiter(",");
        int id = getInteger(scanner.next());
        String name = getString(scanner.next());
        int budget = getInteger(scanner.next());
        int month = getInteger(scanner.next());
        int amount = getInteger(scanner.next());
        boolean isIncome = getBoolean(scanner.next());
        return new ResultCategory(id, name, budget, month, amount, isIncome);
    }

    public static List<ResultCategory> getResultList(String resultList) {
        if (resultList.charAt(0) == '[' && resultList.charAt(1) == ']') {
            return new ArrayList<>();
        }
        List<ResultCategory> results = new ArrayList<>();
        Scanner scanner = new Scanner(resultList);
        scanner.useDelimiter("},");
        while (scanner.hasNext()) {
            results.add(getResult(scanner.next()));
        }
        return results;
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
        Scanner scanner = new Scanner(dateString);
        scanner.useDelimiter(":");
        scanner.next();
        String isolatedDate = scanner.next();
        String year = isolatedDate.substring(1, 5);
        String month = isolatedDate.substring(6, 8);
        String day = isolatedDate.substring(9, 11);
        return DateConversion.getDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    static boolean getBoolean(String booleanString) {
        Scanner scanner = new Scanner(booleanString);
        scanner.useDelimiter(":");
        scanner.next();
        String result = scanner.next();
        return result.contains("true");
    }
}
