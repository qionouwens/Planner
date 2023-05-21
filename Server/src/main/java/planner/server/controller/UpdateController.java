package planner.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planner.commons.UpdateDay;
import planner.database.controller.UpdateDBController;

import java.util.List;

@RestController
@RequestMapping("/api/update")
public class UpdateController {
    private final UpdateDBController updateDBController = new UpdateDBController();

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok(updateDBController.getCategories());
    }

    @GetMapping("/{year}/{month}/{day}")
    public ResponseEntity<UpdateDay> getForDay(@PathVariable("year") int year, @PathVariable("month") int month,
                                               @PathVariable("day") int day) {
        return ResponseEntity.ok(updateDBController.getForDay(year, month, day));
    }

    @PutMapping("/")
    public void update(@RequestBody UpdateDay updateDay) {
        updateDBController.update(updateDay);
    }
}
