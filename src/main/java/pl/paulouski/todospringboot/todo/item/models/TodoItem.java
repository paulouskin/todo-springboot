package pl.paulouski.todospringboot.todo.item.models;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class TodoItem implements Toggleable{

    private String title;
    private String description;
    private TodoItemStatus status;
    private final String id;

    public TodoItem(String title, String description) {
        this.title = title;
        this.description = description;
        status = TodoItemStatus.PENDING;
        id = UUID.randomUUID().toString();
    }

    public TodoItem() {
        id = UUID.randomUUID().toString();
    }

    public TodoItem(TodoItem source) {
        this.title = source.getTitle();
        this.description = source.getDescription();
        status = TodoItemStatus.PENDING;
        id = UUID.randomUUID().toString();
    }

    public static TodoItem of(String title, String description) {
        return new TodoItem(title, description);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TodoItemStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void complete() {
        this.status = TodoItemStatus.COMPLETED;
    }

    @Override
    public void onHold() {
        this.status = TodoItemStatus.PENDING;
    }

    @Override
    public void startProgress() {
        this.status = TodoItemStatus.IN_PROGRESS;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", id='" + id + '\'' +
                '}';
    }
}
