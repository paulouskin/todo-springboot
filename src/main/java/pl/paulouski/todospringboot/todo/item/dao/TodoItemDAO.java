package pl.paulouski.todospringboot.todo.item.dao;

import pl.paulouski.todospringboot.todo.item.models.TodoItem;

import java.util.List;
import java.util.Optional;

public interface TodoItemDAO {

    void save(TodoItem item);

    Optional<TodoItem> findById(String id);

    List<TodoItem> findAll();

    long count();

    void delete(TodoItem item);

    boolean existById(String id);
}
