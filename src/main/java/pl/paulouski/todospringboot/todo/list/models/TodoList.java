package pl.paulouski.todospringboot.todo.list.models;

import pl.paulouski.todospringboot.todo.item.models.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoList {

    private String title;
    private final String id;
    private List<TodoItem> list;

    public TodoList(String title) {
        this.title = title;
        this.id = UUID.randomUUID().toString();
        list = new ArrayList<>();
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

    public void add(TodoItem item) {
        this.list.add(item);
        item.setListId(this.getId());
    }

    public boolean containsItem(String title) {
        return list.stream().anyMatch(item -> item.getTitle().equals(title));
    }
}
