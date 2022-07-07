package pl.paulouski.todospringboot.todo.list.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pl.paulouski.todospringboot.todo.errorhandlers.ErrorDetails;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;
import pl.paulouski.todospringboot.todo.list.models.DeleteResponse;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.services.TodoListService;

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
        String query = "/list/" + listId;
        ResponseEntity<TodoList> entity = template.getForEntity(query, TodoList.class);
        Optional<TodoList> list = Optional.ofNullable(entity.getBody());
        list.ifPresent(
                list1 -> assertEquals("Test list", list1.getTitle())
        );
    }
    @Test
    public void shouldDeleteListById() {
        listService.save(list);
        ResponseEntity<DeleteResponse> entity =
                template.exchange("/list/" + listId, HttpMethod.DELETE, null, DeleteResponse.class);
        Optional<DeleteResponse> msg = Optional.ofNullable(entity.getBody());
        msg.ifPresent(Assertions::assertNotNull);

    }
    @Test
    public void shouldGetOnlyListParametersWithoutQuery() {
        listService.save(list);
        ResponseEntity<TodoList[]> lists = template.getForEntity("/list", TodoList[].class);
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
    public void shouldFailWhileFetchingNonExistingList() {
        ResponseEntity<ErrorDetails> entity = template.getForEntity("/list/0000", ErrorDetails.class);
        Optional<ErrorDetails> error = Optional.ofNullable(entity.getBody());
        error.ifPresent(
                errorDetails -> assertEquals("List with specified id not found.", errorDetails.getMessage())
        );
    }
    @Test
    public void shouldGetAllListItems() {
        listService.save(list);
        String endpoint = String.format("/list/%s/item", listId);
        ResponseEntity<TodoItem[]> response = template.getForEntity(endpoint, TodoItem[].class);
        Optional.ofNullable(response.getBody()).ifPresent(
                items -> assertEquals("Test 1", items[0].getTitle())
        );
    }
    @Test
    public void shouldGetListItemById() {
        listService.save(list);
        var item = list.getItems().get(0);
        String endpoint = String.format("/list/%s/item/%s", listId, item.getId());
        ResponseEntity<TodoItem> response = template.getForEntity(endpoint, TodoItem.class);
        Optional.ofNullable(response.getBody()).ifPresent(
                item1 -> assertEquals("Test 1", item.getTitle())
        );
    }
    @Test
    public void shouldFailWhileFetchingNonExistingListItem() {
        listService.save(list);
        String fakeId = "62862hjhj288";
        String endpoint = String.format("/list/%s/item/%s", listId, fakeId);
        ResponseEntity<ErrorDetails> response = template.getForEntity(endpoint, ErrorDetails.class);
        Optional.ofNullable(response.getBody()).ifPresent(
                error -> Assertions.assertEquals("Item with specified id not found.", error.getMessage())
        );
    }

    @Test
    public void shouldAddItemIntoList() {
        listService.save(list);
        String endpoint = String.format("/list/%s/item", listId);
        var newItem = itemService.create("Brand new item", "Some interesting need to be done");
        ResponseEntity<TodoItem> response = template.postForEntity(endpoint, newItem, TodoItem.class);
        Optional.ofNullable(response.getBody()).ifPresent(
                item -> Assertions.assertEquals(listId, item.getListId())
        );
    }

    @Test
    public void shouldFailWhileAddingItemIntoNonExistingList(){
        String fakeId = "62862hjhj288";
        String endpoint = String.format("/list/%s/item", fakeId);
        var newItem = itemService.create("Brand new item", "Some interesting need to be done");
        ResponseEntity<ErrorDetails> response = template.postForEntity(endpoint, newItem, ErrorDetails.class);
        Optional.ofNullable(response.getBody()).ifPresent(
                error -> Assertions.assertNotNull(error.getMessage())
        );
    }
}
