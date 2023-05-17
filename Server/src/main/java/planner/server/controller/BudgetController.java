package planner.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planner.commons.ResultCategory;
import planner.commons.Statement;
import planner.commons.StatementCategory;
import planner.commons.helper.ClassParser;
import planner.server.services.BudgetService;

import java.util.List;

@RestController
@RequestMapping("api/budget")
public class BudgetController {
    private final BudgetService budgetService = new BudgetService();
    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<ResultCategory>> getResultForThisMonth(@PathVariable("year") int year, @PathVariable("month") int month) {
        return ResponseEntity.ok(budgetService.getResultFromMonth(year, month));
    }

    @GetMapping("/{year}")
    public ResponseEntity<List<ResultCategory>> getResultForYear(@PathVariable("year") int year) {
        return ResponseEntity.ok(budgetService.getAllForYear(year));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Statement>> getRecent() {
        return ResponseEntity.ok(budgetService.getRecent());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<StatementCategory>> getCategories() {
        return ResponseEntity.ok(budgetService.getCategories());
    }

    @PostMapping("/statement")
    public ResponseEntity<Boolean> addStatement(@RequestBody Statement statement) {
        budgetService.addStatement(statement);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/category")
    public ResponseEntity<Boolean> addCategory(@RequestBody StatementCategory statementCategory) {
        budgetService.addCategory(statementCategory);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/statement")
    public void updateStatement(@RequestBody Statement statement) {
        budgetService.updateStatement(statement);
    }

    @PutMapping("/category")
    public ResponseEntity<StatementCategory> updateCategory(@RequestBody String category) {
        StatementCategory statementCategory = ClassParser.getCategory(category);
        return ResponseEntity.ok(budgetService.updateBudget(statementCategory));
    }

    @DeleteMapping("/{id}")
    public void deleteStatement(@PathVariable("id") int id) {
        budgetService.deleteStatement(id);
    }
}
