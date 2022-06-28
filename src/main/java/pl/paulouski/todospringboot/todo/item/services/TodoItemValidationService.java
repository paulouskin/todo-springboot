package pl.paulouski.todospringboot.todo.item.services;

import org.springframework.stereotype.Service;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
@Service
public class TodoItemValidationService {

    private List<Predicate<TodoItem>> validations = new ArrayList<>();

    private Predicate<TodoItem> descriptionLengthLTE250Chars = item -> item.getDescription().length() <= 250;
    private Predicate<TodoItem> titleGT0Chars = item -> item.getTitle().length() > 0;

    {
        validations.add(descriptionLengthLTE250Chars);
        validations.add(titleGT0Chars);
    }

    public TodoItemValidationService() {
    }

    public boolean isValid(final TodoItem item) {
        return validations.parallelStream().allMatch(
                predicate -> predicate.test(item)
        );
    }

}
