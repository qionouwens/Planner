package planner.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planner.commons.Streef;
import planner.commons.Training;
import planner.database.controller.TrainingDBController;

import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController {
    @GetMapping("")
    public ResponseEntity<List<Training>> getTraining() {
        return ResponseEntity.ok(TrainingDBController.getTraining());
    }

    @GetMapping("/streef")
    public ResponseEntity<List<Streef>> getStreef() {
        return ResponseEntity.ok(TrainingDBController.getStreef());
    }

    @PostMapping("")
    public void addTraining(@RequestBody Training training) {
        TrainingDBController.addTraining(training);
    }

    @PostMapping("/streef")
    public void addStreef(@RequestBody Streef streef) {
        TrainingDBController.addStreef(streef);
    }

    @PutMapping("")
    public void updateStreef(@RequestBody Streef streef) {
        TrainingDBController.updateStreef(streef);
    }
}
