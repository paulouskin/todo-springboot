package pl.paulouski.todospringboot.todo.item.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.paulouski.todospringboot.todo.item.exceptions.InvalidTodoItemParametersException;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;


@SpringBootTest
public class TodoItemServiceTest {

    @Autowired
    private TodoItemService service;

    @Test
    public void shouldCreateATodoItem() {
        TodoItem item = service.create("Service item 1", "Service item description");
        Assertions.assertAll(
                () -> Assertions.assertEquals("Service item 1", item.getTitle()),
                () -> Assertions.assertEquals("Service item description", item.getDescription()),
                () -> Assertions.assertNotNull(item.getId())
        );
    }

    @Test
    public void shouldThrowAnExceptionForInvalidConstructorArguments() {
        Assertions.assertThrows(
                InvalidTodoItemParametersException.class,
                () -> service.create("", "Item with invalid title")
        );
    }


}
