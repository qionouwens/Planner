package planner.commons.helper;

import org.junit.jupiter.api.Test;
import planner.commons.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassParserTest {

    @Test
    void convert() {
        String testString = "{\"id\":3,\"title\":\"testTitle\",\"date\":\"2023-05-01T00:00:00.000+00:00\",\"startTime\":\"9:15\",\"endTime\":\"11:15\",\"colour\":\"#ffffff\",\"todoList\":[]}";
        GregorianCalendar calendar = DateConversion.getDate(2023, 5, 1);
        CalendarItem calendarItem = new CalendarItem(3, "testTitle", calendar, "9:15", "11:15", "#ffffff", new ArrayList<>());
        assertEquals(calendarItem, ClassParser.parseCalendar(testString));
    }

    @Test
    void getInteger() {
        String testString = "{\"id\":3";
        assertEquals(3, ClassParser.getInteger(testString));
    }

    @Test
    void getCalendarList() {
        String testString = "[{\"id\":1,\"title\":\"Probstat Exam\",\"date\":\"2023-05-25T00:00:00.000+00:00\",\"startTime\":\"9:00\",\"endTime\":\"11:00\",\"colour\":\"#ff0000\",\"todoList\":[{\"id\":1,\"description\":\"Hello\",\"date\":\"2023-05-25T00:00:00.000+00:00\"}]},{\"id\":2,\"title\":\"Do Project\",\"date\":\"2023-05-25T00:00:00.000+00:00\",\"startTime\":\"12:00\",\"endTime\":\"15:00\",\"colour\":\"#00ff00\",\"todoList\":[]}]";
        List<Todo> todoList = new ArrayList<>();
        todoList.add(new Todo(1, "Hello", DateConversion.getDate(2023, 5, 25)));
        CalendarItem first = new CalendarItem(1, "Probstat Exam", DateConversion.getDate(2023, 5, 25), "9:00", "11:00", "#ff0000", todoList);
        CalendarItem second = new CalendarItem(2, "Do Project", DateConversion.getDate(2023, 5, 25), "12:00", "15:00", "#00ff00", new ArrayList<>());
        List<CalendarItem> calendarItems = ClassParser.parseCalendarList(testString);
        assertTrue(calendarItems.contains(first));
        assertTrue(calendarItems.contains(second));
    }

    @Test
    void getInventoryItemTest() {
        InventoryItem inventoryItem = new InventoryItem(1, "cup", 1, "Fridge");
        String result = "{\"id\":1,\"item\":\"cup\",\"quantity\":1,\"location\":\"Fridge\"}";
        assertEquals(inventoryItem, ClassParser.getInventoryItem(result));
    }

    @Test
    void getTodoTest() {
        Todo todo = new Todo(1, "Hello", DateConversion.getDate(2023, 5, 25));
        String result = "{\"id\":1,\"description\":\"Hello\",\"date\":\"2023-05-25T00:00:00.000+00:00\"}";
        assertEquals(todo, ClassParser.getTodo(result));
    }

    @Test
    void getTrainingTest() {
        TrainingPart trainingPart = new TrainingPart(1, 1000, "3:49.4");
        TrainingPart trainingPart1 = new TrainingPart(2, 1000, "3:49.5");
        Training training = new Training(1, "Ergo", "2x1000", DateConversion.getDate(2023, 6, 5), List.of(trainingPart, trainingPart1));
        String trainingString = "{\"id\":1,\"trainingType\":\"Ergo\",\"description\":\"2x1000\",\"calendar\":\"2023-06-05T00:00:00.000+00:00\",\"trainingParts\":[{\"id\":1,\"distance\":1000,\"time\":\"3:49.4\"},{\"id\":2,\"distance\":1000,\"time\":\"3:49.5\"}]}";
        assertEquals(training, ClassParser.getTraining(trainingString));
    }

    @Test
    void getTrainingListTest() {
        TrainingPart trainingPart = new TrainingPart(1, 1000, "3:49.4");
        TrainingPart trainingPart1 = new TrainingPart(2, 1000, "3:49.5");
        Training training = new Training(1, "Ergo", "2x1000", DateConversion.getDate(2023, 6, 5), List.of(trainingPart, trainingPart1));
        TrainingPart trainingPart2 = new TrainingPart(1, 1300, "8:00");
        TrainingPart trainingPart3 = new TrainingPart(2, 1300, "8:00");
        Training training1 = new Training(2, "Running", "2x8'", DateConversion.getDate(2023, 6, 5), List.of(trainingPart2, trainingPart3));
        String trainingListString = "[{\"id\":1,\"trainingType\":\"Ergo\",\"description\":\"2x1000\",\"calendar\":\"2023-06-05T00:00:00.000+00:00\",\"trainingParts\":[{\"id\":1,\"distance\":1000,\"time\":\"3:49.4\"},{\"id\":2,\"distance\":1000,\"time\":\"3:49.5\"}]},{\"id\":2,\"trainingType\":\"Running\",\"description\":\"2x8'\",\"calendar\":\"2023-06-05T00:00:00.000+00:00\",\"trainingParts\":[{\"id\":1,\"distance\":1300,\"time\":\"8:00\"},{\"id\":2,\"distance\":1300,\"time\":\"8:00\"}]}]";
        List<Training> result = ClassParser.getTrainingList(trainingListString);
        assertTrue(result.contains(training));
        assertTrue(result.contains(training1));
    }

    @Test
    void getStreefTest() {
        Streef streef = new Streef("3x15 ED", "2:10");
        String streefString = "{\"training\":\"3x15 ED\",\"streef\":\"2:10\"}";
        assertEquals(streef, ClassParser.getStreef(streefString));
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