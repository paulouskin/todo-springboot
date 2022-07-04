package pl.paulouski.todospringboot.todo.list.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;
import pl.paulouski.todospringboot.todo.list.models.TodoList;

@SpringBootTest
public class TodoListServiceTest {

    @Autowired
    private TodoListService listService;
    @Autowired
    private TodoItemService itemService;

    private TodoList testList;
    private TodoItem testItem;

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
