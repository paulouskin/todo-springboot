package pl.paulouski.todospringboot.todo.list.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.paulouski.todospringboot.todo.configuration.ProjectConfiguration;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;

public class TodoListTest {

    private TodoList list;
    private TodoItemService service;

    @BeforeEach
    public void classSetUp() {
        list = new TodoList("List for test purpose");
        var ctx = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        service = ctx.getBean(TodoItemService.class);
    }

    @Test
    public void shouldHaveBasicAttributes() {
        Assertions.assertAll(
                () -> Assertions.assertNotNull(list.getId()),
                () -> Assertions.assertEquals("List for test purpose", list.getTitle())
        );
    }



}
