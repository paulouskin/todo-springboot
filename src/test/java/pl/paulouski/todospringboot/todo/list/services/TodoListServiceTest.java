package pl.paulouski.todospringboot.todo.list.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.paulouski.todospringboot.todo.configuration.ProjectConfiguration;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;
import pl.paulouski.todospringboot.todo.list.models.TodoList;

public class TodoListServiceTest {

    private static TodoListService listService;
    private static TodoItemService itemService;

    private TodoList testList;
    private TodoItem testItem;

    @BeforeAll
    public static void classSetUp() {
        var ctx = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        listService = ctx.getBean(TodoListService.class);
        itemService = ctx.getBean(TodoItemService.class);
    }

    @BeforeEach
    public void setUp() {
        testList = listService.createList("Test list 1");
        testItem = itemService.create("Test item 1", "Test description 1");
    }


    @Test
    public void shouldCreateListAndAssignListIdToNewlyAddedItems() {
        testList.add(testItem);
        Assertions.assertAll(
                () -> Assertions.assertTrue(testList.containsItem(testItem.getTitle())),
                () -> Assertions.assertEquals(testItem.getListId(), testList.getId())
        );
    }

}
