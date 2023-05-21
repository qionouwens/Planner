package planner.client.serverUtils;

import planner.commons.UpdateDay;
import planner.commons.helper.ClassParser;
import planner.commons.helper.JSONConverter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UpdateServerUtils {
    private ServerUtils serverUtils = new ServerUtils();

    public List<String> getCategories() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/update/categories"))
                    .GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getStringList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public UpdateDay getUpdateDay(int year, int month, int day) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/update/" + year + "/" + month + "/" + day))
                    .GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getUpdateDay(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendUpdateDay(UpdateDay updateDay) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/update/"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(JSONConverter.convertUpdateDay(updateDay)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
