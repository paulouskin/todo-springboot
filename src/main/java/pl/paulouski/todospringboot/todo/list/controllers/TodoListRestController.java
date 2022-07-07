package pl.paulouski.todospringboot.todo.list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;
import pl.paulouski.todospringboot.todo.list.models.DeleteResponse;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.services.TodoListService;

@RestController
@Transactional
public class TodoListRestController {

    private final TodoListService listService;

    private final TodoItemService itemService;
    @Autowired
    public TodoListRestController(TodoListService listService, TodoItemService itemService) {
        this.listService = listService;
        this.itemService = itemService;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<TodoList> getList(@PathVariable String id){
        return ResponseEntity.ok(listService.getList(id));
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<DeleteResponse> deleteList(@PathVariable String id){
        return ResponseEntity.ok(listService.delete(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<TodoList>> getLists(){
        return ResponseEntity.ok(listService.getAllLists());
    }
    @PostMapping("/list")
    public ResponseEntity<String> createList(@RequestBody()TodoList list){
        return ResponseEntity.ok(listService.save(list));
    }
    @GetMapping("/list/{id}/item")
    public ResponseEntity<Iterable<TodoItem>> getItems(@PathVariable String id){
        return ResponseEntity.ok(listService.getListItems(id));
    }
    @GetMapping("/list/{id}/item/{itemId}")
    public ResponseEntity<TodoItem> getItem(@PathVariable String id, @PathVariable String itemId){
        return ResponseEntity.ok(itemService.getItem(id, itemId));
    }

    @PostMapping("/list/{id}/item")
    public ResponseEntity<TodoItem> addItem(@PathVariable String id, @RequestBody()TodoItem item){
        return ResponseEntity.ok(listService.addItemToList(id, item));
    }




}
