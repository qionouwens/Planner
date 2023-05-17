package planner.client;

import planner.commons.StatementCategory;
import planner.commons.helper.JSONConverter;

public class RunClientCommands {
    public static void main(String[] args) {
        String stmt = "{\"id\":1,\"name\":\"Proteus\",\"budget\":500}";
        StatementCategory statementCategory = new StatementCategory(1, "Proteus", 500);
        System.out.println(JSONConverter.convertStatementCategory(statementCategory));
        System.out.println(stmt.equals(JSONConverter.convertStatementCategory(statementCategory)));
    }
}
