package planner.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planner.database.controller.ValueDBController;

@RestController
@RequestMapping("/api/value")
public class ValueController {
    @GetMapping("weight")
    public ResponseEntity<Integer> getLatestWeight() {
        return ResponseEntity.ok(ValueDBController.getLatestWeight());
    }

    @GetMapping("sleep")
    public ResponseEntity<String> getLatestSleep() {
        return ResponseEntity.ok(ValueDBController.getLatestSleep());
    }

    @PostMapping("weight/{weight}")
    public void updateWeight(@PathVariable("weight") int weight) {
        ValueDBController.updateWeight(weight);
    }

    @PostMapping("sleep")
    public void updateSleep(@RequestBody String sleep) {
        ValueDBController.updateSleep(sleep);
    }
}
