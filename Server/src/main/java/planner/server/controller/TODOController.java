package planner.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planner.commons.Todo;
import planner.database.controller.TodoDBController;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TODOController {
    @GetMapping("")
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(TodoDBController.getTodos());
    }

    @PostMapping("")
    public void addTodo(@RequestBody Todo todo) {
        TodoDBController.addTodo(todo);
    }

    @PostMapping("/{CalId}/{todoId}")
    public void addTodoToCalendar(@PathVariable("CalId") int calId, @PathVariable("todoId") int todoId) {
        TodoDBController.addTodoToCalendar(calId, todoId);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable("id") int id) {
        TodoDBController.deleteTodo(id);
    }
}
