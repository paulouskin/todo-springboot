package pl.paulouski.todospringboot.todo.list.models;

import java.util.UUID;

public class TodoList {

    private String title;
    private final String id;

    public TodoList(String title) {
        this.title = title;
        this.id = UUID.randomUUID().toString();
    }

    public TodoList() {
        this.id = UUID.randomUUID().toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }
}
