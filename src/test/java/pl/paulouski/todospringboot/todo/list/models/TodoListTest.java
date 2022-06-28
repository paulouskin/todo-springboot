package pl.paulouski.todospringboot.todo.list.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;

public class TodoListTest {

    private TodoList list;
    private TodoItemService service;

    @BeforeEach
    public void classSetUp() {
        list = new TodoList("List for test purpose");
        service = new TodoItemService();
    }

    @Test
    public void shouldHaveBasicAttributes() {
        Assertions.assertAll(
                () -> Assertions.assertNotNull(list.getId()),
                () -> Assertions.assertEquals("List for test purpose", list.getTitle())
        );
    }



}
