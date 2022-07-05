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
import pl.paulouski.todospringboot.todo.errorhandlers.ErrorDetails;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.services.TodoListService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoListRestControllerTest {

    @Autowired
    private TodoListService listService;
    @Autowired
    private TodoItemService itemService;
    @Autowired
    TestRestTemplate template;
    private String listId;
    private TodoList list;


    @BeforeEach
    public void setUp() {
        list = listService.createList("Test list");
        list.add(itemService.create("Test 1", "Description 1"));
        list.add(itemService.create("Test 2", "Description 2"));
        listId = list.getId();
    }

    @Test
    public void shouldGetListById(){
        listService.save(list);
        String query = "/list?id="+listId;
        ResponseEntity<TodoList> entity = template.getForEntity(query, TodoList.class);
        Optional<TodoList> list = Optional.ofNullable(entity.getBody());
        list.ifPresent(
                list1 -> assertEquals("Test list", list1.getTitle())
        );
    }
    @Test
    public void shouldGetOnlyListParametersWithoutQuery() {
        listService.save(list);
        ResponseEntity<TodoList[]> lists = template.getForEntity("/lists", TodoList[].class);
        assertEquals(listId, Objects.requireNonNull(lists.getBody())[0].getId());
    }
    @Test
    public void shouldCreateListAndReturnItsId() {
        ResponseEntity<String> entity = template.postForEntity("/list", list, String.class);
        Optional<String> actualListId = Optional.ofNullable(entity.getBody());
        actualListId.ifPresent(
                id -> assertEquals(listId, id)
        );
    }

    @Test
    public void shouldFetchingNonExistingListResultInBadRequest() {
        ResponseEntity<ErrorDetails> entity = template.getForEntity("/list?id=0000", ErrorDetails.class);
        Optional<ErrorDetails> error = Optional.ofNullable(entity.getBody());
        error.ifPresent(
                errorDetails -> assertEquals("List with specified id not found.", errorDetails.getMessage())
        );
    }

}
