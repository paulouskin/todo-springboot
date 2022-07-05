package pl.paulouski.todospringboot.todo.list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.services.TodoListService;

import java.util.List;

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

    @GetMapping("/lists")
    public ResponseEntity<Iterable<TodoList>> getLists(){
        return ResponseEntity.ok(service.getAllLists());
    }

    @PostMapping("/list")
    public ResponseEntity<String> createList(@RequestBody()TodoList list){
        return ResponseEntity.ok(service.save(list));
    }
}
