package planner.commons.helper;

import planner.commons.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassParser {

    private final static Pattern integerPattern = Pattern.compile("\"\\w+\":\\d+");
    private static final Pattern stringPattern = Pattern.compile("\"\\w+\":\"[\\w: #]+\"");
    private static final Pattern datePattern = Pattern.compile("\"\\w+\":\"\\d{4}-\\d{2}-\\d{2}T00:00:00\\.000\\+00:00\"");
    private static final Pattern listPattern = Pattern.compile("\"\\w+\":\\[.*]");
    private static final Pattern booleanPattern = Pattern.compile("\"\\w+\":(true|false)");

    public static List<CalendarItem> parseCalendarList(String calendarString) {
        List<CalendarItem> calendarItems = new ArrayList<>();
        for (String calString : getObjectList(calendarString)) {
            calendarItems.add(parseCalendar(calString));
        }
        return calendarItems;
    }

    public static CalendarItem parseCalendar(String calendarString) {
        Matcher matcher = integerPattern.matcher(calendarString);
        String id = getMatcherResult(matcher, integerPattern);
        String title = getMatcherResult(matcher, stringPattern);
        String date = getMatcherResult(matcher, datePattern);
        String startTime = getMatcherResult(matcher, stringPattern);
        String endTime = getMatcherResult(matcher, stringPattern);
        String colour = getMatcherResult(matcher, stringPattern);
        String todoList = getMatcherResult(matcher, listPattern);
        return new CalendarItem(getInteger(id), getString(title), getDate(date), getString(startTime), getString(endTime), getString(colour), getTodoList(todoList));
    }

    public static Todo getTodo(String todo) {
        Matcher matcher = integerPattern.matcher(todo);
        String id = getMatcherResult(matcher, integerPattern);
        String description = getMatcherResult(matcher, stringPattern);
        String date = getMatcherResult(matcher, datePattern);
        return new Todo(getInteger(id), getString(description), getDate(date));
    }

    public static List<Todo> getTodoList(String todoList) {
        List<Todo> result = new ArrayList<>();
        for (String todo : getObjectList(todoList)) {
            result.add(getTodo(todo));
        }
        return result;
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

    public static List<String> getStringList(String stringList) {
        List<String> result = new ArrayList<>();
        if (stringList.equals("[]")) {
            return result;
        }
        stringList = stringList.replaceAll("[\\[\\]\"]", "");
        Scanner scanner = new Scanner(stringList);
        scanner.useDelimiter(",");
        while(scanner.hasNext()) {
            result.add(scanner.next());
        }
        return result;
    }

    public static UpdateDay getUpdateDay(String updateDayString) {
        Pattern listPattern = Pattern.compile("\\[.*]");
        Scanner scanner = new Scanner(updateDayString);
        scanner.useDelimiter(",");
        String dateString = scanner.next();
        GregorianCalendar calendar = getDate(dateString);
        Matcher matcher = listPattern.matcher(updateDayString);
        String stringList = null;
        if (matcher.find()) {
            stringList = matcher.group();
        }
        List<String> cats = getStringList(stringList);
        return new UpdateDay(calendar, cats);
    }

    public static GroceryItem getGroceryItem(String groceryItemString) {
        Matcher matcher = integerPattern.matcher(groceryItemString);
        String id = getMatcherResult(matcher, integerPattern);
        String item = getMatcherResult(matcher, stringPattern);
        String quantity = getMatcherResult(matcher, integerPattern);
        String priority = getMatcherResult(matcher, stringPattern);
        String type = getMatcherResult(matcher, stringPattern);
        return new GroceryItem(getInteger(id), getString(item), getInteger(quantity), getString(priority), getString(type));
    }

    public static List<GroceryItem> getGroceryItemList(String groceryItemListString) {
        List<GroceryItem> groceryItems = new ArrayList<>();
        for (String groceryItemString : getObjectList(groceryItemListString)) {
            groceryItems.add(getGroceryItem(groceryItemString));
        }
        return groceryItems;
    }

    public static InventoryItem getInventoryItem(String inventoryItemString) {
        Matcher matcher = integerPattern.matcher(inventoryItemString);
        String id = getMatcherResult(matcher, integerPattern);
        String item = getMatcherResult(matcher, stringPattern);
        String quantity = getMatcherResult(matcher, integerPattern);
        String location = getMatcherResult(matcher, stringPattern);
        return new InventoryItem(getInteger(id), getString(item), getInteger(quantity), getString(location));
    }

    public static List<InventoryItem> getInventoryItemList(String inventoryItemListString) {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        for (String inventoryString : getObjectList(inventoryItemListString)) {
            inventoryItems.add(getInventoryItem(inventoryString));
        }
        return inventoryItems;
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

    static List<String> getObjectList(String objectList) {
        List<String> stringList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int amountOfBrackets = 0;
        boolean isObject = false;
        for (int i = 0; i < objectList.length(); i++) {
            if (objectList.charAt(i) == '{') {
                amountOfBrackets++;
                isObject = true;
            }
            if (objectList.charAt(i) == '}') {
                amountOfBrackets--;
                if (amountOfBrackets == 0) {
                    isObject = false;
                    stringList.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }
            if (isObject) {
                stringBuilder.append(objectList.charAt(i));
            }
        }
        return stringList;
    }

    public static String getMatcherResult(Matcher matcher, Pattern pattern) {
        matcher.usePattern(pattern);
        matcher.find();
        return matcher.group();
    }
}
