package planner.client.serverUtils;

public class ServerUtils {
    private static final String server = "http://localhost:8080";

    public ServerUtils() {}

    public String getServer() {
        return server;
    }

    public static String getServerString() {
        return server;
    }
}
