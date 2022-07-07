package pl.paulouski.todospringboot.todo.errorhandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.paulouski.todospringboot.todo.item.exceptions.ItemNotFoundException;
import pl.paulouski.todospringboot.todo.list.exceptions.TodoListNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(TodoListNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionListNotFoundHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("List with specified id not found.");
        return ResponseEntity.badRequest().body(errorDetails);
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionItemNotFoundHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Item with specified id not found.");
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
