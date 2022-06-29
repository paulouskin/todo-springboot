package pl.paulouski.todospringboot.todo.item.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.paulouski.todospringboot.todo.configuration.ProjectConfiguration;
import pl.paulouski.todospringboot.todo.item.exceptions.InvalidTodoItemParametersException;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;

public class TodoItemServiceTest {

    private static TodoItemService service;

    @BeforeAll
    public static void classSetUp() {
        var ctx = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        service = ctx.getBean(TodoItemService.class);
    }

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
