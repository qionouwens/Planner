package planner.commons.helper;

import org.junit.jupiter.api.Test;
import planner.commons.*;

import java.util.GregorianCalendar;
import java.util.List;

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

    @Test
    void convertGroceryItem() {
        String result = "{\"id\":1,\"item\":\"Chip\",\"quantity\":1,\"priority\":\"High\",\"type\":\"Grocery\"}";
        GroceryItem groceryItem = new GroceryItem(1, "Chip", 1, "High", "Grocery");
        assertEquals(result, JSONConverter.convertGroceryItem(groceryItem));
    }

    @Test
    void convertInventoryItem() {
        String result = "{\"id\":1,\"item\":\"cup\",\"quantity\":1,\"location\":\"Fridge\"}";
        InventoryItem inventoryItem = new InventoryItem(1, "cup", 1, "Fridge");
        assertEquals(result, JSONConverter.convertInventoryItem(inventoryItem));
    }

    @Test
    void convertTodo() {
        String result = "{\"id\":1,\"description\":\"Hello\",\"date\":\"2023-05-25T00:00:00.000+00:00\"}";
        Todo todo = new Todo(1, "Hello", DateConversion.getDate(2023, 5, 25));
        assertEquals(result, JSONConverter.convertTodo(todo));
    }

    @Test
    void convertTraining() {
        String result = "{\"id\":1,\"trainingType\":\"Ergo\",\"description\":\"2x1000\",\"calendar\":\"2023-06-05T00:00:00.000+00:00\",\"trainingParts\":[{\"id\":1,\"distance\":1000,\"time\":\"3:49.4\"},{\"id\":2,\"distance\":1000,\"time\":\"3:49.5\"}]}";
        TrainingPart trainingPart = new TrainingPart(1, 1000, "3:49.4");
        TrainingPart trainingPart1 = new TrainingPart(2, 1000, "3:49.5");
        Training training = new Training(1, "Ergo", "2x1000", DateConversion.getDate(2023, 6, 5), List.of(trainingPart, trainingPart1));
        assertEquals(result, JSONConverter.convertTraining(training));
    }

    @Test
    void convertStreef() {
        String result = "{\"training\":\"3x15 ED\",\"streef\":\"2:10\"}";
        Streef streef = new Streef("3x15 ED", "2:10");
        assertEquals(result, JSONConverter.convertStreef(streef));
    }
}