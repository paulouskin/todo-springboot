package pl.paulouski.todospringboot.todo.list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulouski.todospringboot.todo.list.exceptions.TodoListNotFoundException;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.dao.JdbcTodoListDAO;

@Service
public class TodoListService {

    JdbcTodoListDAO repository;

    @Autowired
    public TodoListService(JdbcTodoListDAO repository) {
        this.repository = repository;
    }

    public JdbcTodoListDAO getRepository() {
        return repository;
    }

    public void setRepository(JdbcTodoListDAO repository) {
        this.repository = repository;
    }

    public TodoList createList(String title) {
        return new TodoList(title);
    }

    public TodoList getList(String listId) {
        return repository.findById(listId).orElseThrow(TodoListNotFoundException::new);
    }

    public void save(TodoList list) {
        repository.save(list);
    }
}
