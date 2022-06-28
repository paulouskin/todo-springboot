package pl.paulouski.todospringboot.todo.item.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulouski.todospringboot.todo.item.exceptions.InvalidTodoItemParametersException;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemValidationService validationService;

    public TodoItemService() {
        this.validationService = new TodoItemValidationService();
    }

    public TodoItemService(TodoItemValidationService validationService) {
        this.validationService = validationService;
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


}
