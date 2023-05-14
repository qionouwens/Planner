package planner.client.serverUtils;

import planner.commons.ResultCategory;
import planner.commons.Statement;
import planner.commons.StatementCategory;
import planner.commons.helper.ClassParser;
import planner.commons.helper.JSONConverter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BudgetServerUtils {
    private final ServerUtils serverUtils;

    public BudgetServerUtils() {
        this.serverUtils = new ServerUtils();
    }

    public List<StatementCategory> getCategories() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget" + "/categories"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getCategoryList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Statement> getRecent() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget" + "/recent"))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getStatementList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ResultCategory> getResultForYear(int year) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget" + "/" + year))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getResultList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ResultCategory> getResultForThisMonth(int year, int month) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget" + "/" + year + "/" + month))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return ClassParser.getResultList(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategory(StatementCategory statementCategory) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget/category"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(JSONConverter.convertStatementCategory(statementCategory)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStatement(Statement statement) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget/statement"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(JSONConverter.convertStatement(statement)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCategory(StatementCategory statementCategory) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget/category"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(JSONConverter.convertStatementCategory(statementCategory)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatement(Statement statement) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget/statement"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(JSONConverter.convertStatement(statement)))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStatement(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUtils.getServer() + "/api/budget/" + id))
                    .DELETE()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
