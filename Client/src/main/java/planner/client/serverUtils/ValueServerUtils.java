package planner.client.serverUtils;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ValueServerUtils {
    private static final ServerUtils serverUtils = new ServerUtils();

    public static int getLatestWeight() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/value/weight"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return Integer.parseInt(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLatestSleep() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/value/sleep"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateWeight(int weight) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/value/weight/" + weight))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateSleep(String sleep) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/value/sleep"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(sleep))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
