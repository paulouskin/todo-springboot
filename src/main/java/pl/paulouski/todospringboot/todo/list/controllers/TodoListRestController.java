package pl.paulouski.todospringboot.todo.list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.services.TodoListService;

@RestController
@Transactional
public class TodoListRestController {

    private final TodoListService service;

    public TodoListRestController(TodoListService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<TodoList> getList(@RequestParam(name = "id", required = false)String listId){
        return ResponseEntity.ok(service.getList(listId));
    }
}
