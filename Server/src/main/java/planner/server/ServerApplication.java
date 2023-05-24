package planner.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import planner.database.CreateDatabase;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        CreateDatabase.create();
        SpringApplication.run(ServerApplication.class, args);

    }

}
