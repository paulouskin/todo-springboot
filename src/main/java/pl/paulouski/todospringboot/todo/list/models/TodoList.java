package pl.paulouski.todospringboot.todo.list.models;

import pl.paulouski.todospringboot.todo.item.models.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoList {

    private String title;
    private final String id;
    private List<TodoItem> items;

    public TodoList(String title) {
        this.title = title;
        this.id = UUID.randomUUID().toString();
        items = new ArrayList<>();
    }

    public TodoList(String id, String title) {
        this.title = title;
        this.id = id;
        items = new ArrayList<>();
    }

    public TodoList() {
        this.id = UUID.randomUUID().toString();
        items = new ArrayList<>();
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
        this.items.add(item);
        item.setListId(this.getId());
    }

    public void addAll(List<TodoItem> items) {
        this.items.addAll(items);
        items.forEach(item -> item.setListId(this.getId()));
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public boolean containsItem(String title) {
        return items.stream().anyMatch(item -> item.getTitle().equals(title));
    }
}
