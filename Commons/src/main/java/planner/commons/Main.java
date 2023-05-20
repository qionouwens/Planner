package planner.commons;

import planner.commons.helper.ClassParser;

public class Main {
    public static void main(String[] args) {
        Statement statement = ClassParser.getStatement("{\"id\":0,\"amount\":9318,\"category\":\"Tikkies\",\"date\":\"2023-01-06T00:00:00.000+00:00\",\"income\":true}");
    }
}