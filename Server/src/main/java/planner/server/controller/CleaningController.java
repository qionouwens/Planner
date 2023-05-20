package planner.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import planner.commons.CleaningTask;
import planner.database.controller.CleaningDBController;

import java.util.List;

@RestController
@RequestMapping("api/cleaning")
public class CleaningController {
    private final CleaningDBController cleaningDBController = new CleaningDBController();
    @GetMapping("/")
    public ResponseEntity<List<CleaningTask>> getTasks() {
        return ResponseEntity.ok(cleaningDBController.getTasks());
    }

    @PostMapping("/")
    public void doTask(String task) {
        cleaningDBController.doTask(task);
    }
}
