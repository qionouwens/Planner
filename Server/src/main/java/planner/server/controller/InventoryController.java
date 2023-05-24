package planner.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planner.commons.InventoryItem;
import planner.database.controller.InventoryDBController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @GetMapping()
    public ResponseEntity<List<InventoryItem>> getAll() {
        return ResponseEntity.ok(InventoryDBController.getAll());
    }

    @PostMapping()
    public void addInventory(InventoryItem inventoryItem) {
        InventoryDBController.addItem(inventoryItem);
    }

    @DeleteMapping("{id}")
    public void deleteInventory(@PathVariable("id") int id) {
        InventoryDBController.deleteItem(id);
    }
}
