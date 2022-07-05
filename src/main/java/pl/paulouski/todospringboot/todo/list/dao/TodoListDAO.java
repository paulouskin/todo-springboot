package pl.paulouski.todospringboot.todo.list.dao;

import pl.paulouski.todospringboot.todo.list.models.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListDAO {

    String save(TodoList item);

    Optional<TodoList> findById(String id);

    List<TodoList> findAll();

    long count();

    void delete(TodoList list);

    boolean existById(String id);

}
