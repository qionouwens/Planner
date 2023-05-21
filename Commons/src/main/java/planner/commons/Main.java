package planner.commons;

import planner.commons.helper.ClassParser;
import planner.commons.helper.DateConversion;
import planner.commons.helper.JSONConverter;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Ontbeten");
        stringList.add("Gelunched");
        GregorianCalendar calendar = DateConversion.getDate(2023, 5, 21);
        UpdateDay updateDay = new UpdateDay(calendar, stringList);
        System.out.println(JSONConverter.convertUpdateDay(updateDay));
        System.out.println(JSONConverter.convertUpdateDay(updateDay).equals("{\"calendar\":\"2023-05-21T00:00:00.000+00:00\",\"categoryMap\":[\"Ontbeten\",\"Gelunched\"]}"));
    }
}