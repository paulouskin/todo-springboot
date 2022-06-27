package pl.paulouski.todospringboot.todo.item.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoItemTest {

    TodoItem item;

    @BeforeEach
    public void setUp() {
        item = TodoItem.of("Test todo", "Make test working!");
    }

    @Test
    public void shouldHaveATitleAndADescription() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("Test todo", item.getTitle()),
                () -> Assertions.assertEquals("Make test working!", item.getDescription())

        );
    }

    @Test
    public void shouldChangeItemTitle() {
        String newTitle = "Item new title";
        Assertions.assertTrue(
                () -> {
                    item.setTitle(newTitle);
                    return newTitle.equals(item.getTitle());
                }
        );
    }

    @Test
    public void shouldChangeItemDescription() {
        String newDescription = "Item new description";
        Assertions.assertTrue(
                () -> {
                    item.setDescription(newDescription);
                    return newDescription.equals(item.getDescription());
                }
        );
    }

    @Test
    public void shouldHaveDefaultStatusPendingAfterCreation() {
        Assertions.assertEquals(TodoItemStatus.PENDING, item.getStatus());
    }

    @Test
    public void shouldHaveCompletedStatusAfterCompletion() {
        Assertions.assertTrue(
                () -> {
                    item.complete(); return TodoItemStatus.COMPLETED.equals(item.getStatus());
                }
        );
    }

    @Test
    public void shouldHaveInProgressStatusWhenProgressStarted() {
        Assertions.assertTrue(
                () -> {
                    item.startProgress(); return TodoItemStatus.IN_PROGRESS.equals(item.getStatus());
                }
        );
    }

    @Test
    public void shouldHavePendingStatusWhenPutOnHold() {
        Assertions.assertTrue(
                () -> {
                    item.startProgress();
                    item.onHold();
                    return TodoItemStatus.PENDING.equals(item.getStatus());
                }
        );
    }

}
