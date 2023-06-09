package planner.client.serverUtils;

import planner.commons.Todo;
import planner.commons.helper.ClassParser;
import planner.commons.helper.JSONConverter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TodoServerUtils {
    public static final ServerUtils serverUtils = new ServerUtils();
    public static List<Todo> getTodos() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/todo"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getTodoList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTodo(Todo todo) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/todo"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(JSONConverter.convertTodo(todo)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTodoToCalendar(int calId, int todoId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/todo/" + calId + "/" + todoId))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTodo(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/todo/" + id))
                    .DELETE()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
