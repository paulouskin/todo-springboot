package pl.paulouski.todospringboot.todo.list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.repositories.TodoListRepository;

@Service
public class TodoListService {

    TodoListRepository repository;

    @Autowired
    public TodoListService(TodoListRepository repository) {
        this.repository = repository;
    }

    public TodoListRepository getRepository() {
        return repository;
    }

    public void setRepository(TodoListRepository repository) {
        this.repository = repository;
    }

    public TodoList createList(String title) {
        return new TodoList(title);
    }
}
