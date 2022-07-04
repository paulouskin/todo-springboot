package pl.paulouski.todospringboot.todo.list.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.transaction.annotation.Transactional;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.services.TodoListService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoListRestControllerTest {

    @Autowired
    private TodoListService listService;
    @Autowired
    private TodoItemService itemService;
    private static String listId;


    @BeforeEach
    public void setUp() {
        var list = listService.createList("Test list");
        list.add(itemService.create("Test 1", "Description 1"));
        list.add(itemService.create("Test 2", "Description 2"));
        listService.save(list);
        listId = list.getId();
    }

    @Test
    public void shouldGetListById(@Autowired TestRestTemplate template){
        String query = "/list?id="+listId;
        ResponseEntity<TodoList> entity = template.getForEntity(query, TodoList.class);
        Optional<TodoList> list = Optional.ofNullable(entity.getBody());
        list.ifPresent(
                list1 -> assertEquals("Test list", list1.getTitle())
        );
    }



}
