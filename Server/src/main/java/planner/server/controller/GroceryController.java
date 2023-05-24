package planner.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planner.commons.GroceryItem;
import planner.database.controller.GroceryDBController;

import java.util.List;

@RestController
@RequestMapping("api/grocery")
public class GroceryController {
    @GetMapping("")
    public ResponseEntity<List<GroceryItem>> getAll() {
        return ResponseEntity.ok(GroceryDBController.getAll());
    }

    @GetMapping("/priority")
    public ResponseEntity<List<String>> getPriorities() {
        return ResponseEntity.ok(GroceryDBController.getPriorities());
    }

    @PostMapping("")
    public void addGrocery(@RequestBody GroceryItem groceryItem) {
        GroceryDBController.addGrocery(groceryItem);
    }

    @DeleteMapping("/{id}")
    public void deleteGrocery(@PathVariable("id") int id) {
        GroceryDBController.deleteGrocery(id);
    }

}
