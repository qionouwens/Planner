package planner.client.serverUtils;

import planner.commons.Streef;
import planner.commons.Training;
import planner.commons.helper.ClassParser;
import planner.commons.helper.JSONConverter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TrainingServerUtils {
    private static final ServerUtils serverUtils = new ServerUtils();

    public static List<Training> getTraining() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/training"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getTrainingList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Streef> getStreef() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/training/streef"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getStreefList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTraining(Training training) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/training"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(JSONConverter.convertTraining(training)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addStreef(Streef streef) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/training/streef"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(JSONConverter.convertStreef(streef)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTraining(Training training) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/training/"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(JSONConverter.convertTraining(training)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateStreef(Streef streef) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/training/streef"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(JSONConverter.convertStreef(streef)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
