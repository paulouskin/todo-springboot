package pl.paulouski.todospringboot.todo.item.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulouski.todospringboot.todo.item.exceptions.InvalidTodoItemParametersException;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;
import pl.paulouski.todospringboot.todo.item.dao.JdbcTodoItemDAO;

import java.util.List;

@Service
public class TodoItemService {

    private TodoItemValidationService validationService;
    private JdbcTodoItemDAO repository;
    public TodoItemService() {
    }

    @Autowired
    public TodoItemService(TodoItemValidationService validationService, JdbcTodoItemDAO repository) {
        this.validationService = validationService;
        this.repository = repository;
    }

    public TodoItem create(String title, String description) {
        var todo = TodoItem.of(title, description);
        if (validationService.isValid(todo))
            return todo;
        else
            throw new InvalidTodoItemParametersException("Input data for todo item is invalid!");
    }

    public TodoItem create(String title) {
        return create(title, "");
    }

    public TodoItem updateTitle(TodoItem item, String newTitle) {
        var todo = new TodoItem(item);
        todo.setTitle(newTitle);
        return getTodoItem(item, todo);
    }

    public TodoItem updateDescription(TodoItem item, String newDescription) {
        var todo = new TodoItem(item);
        todo.setDescription(newDescription);
        return getTodoItem(item, todo);
    }
    private TodoItem getTodoItem(TodoItem item, TodoItem todo) {
        if (validationService.isValid(todo)) {
            return todo;
        } else {
            return item;
        }
    }

    public TodoItemValidationService getValidationService() {
        return validationService;
    }

    public void setValidationService(TodoItemValidationService validationService) {
        this.validationService = validationService;
    }

    public void delete(TodoItem item) {
        repository.delete(item);
    }
    public void save(TodoItem item) {
        repository.save(item);
    }
    public List<TodoItem> getItemsForList(String listId) {
        return repository.getItemsForList(listId);
    }
}
