package pl.paulouski.todospringboot.todo.item.exceptions;

public class InvalidTodoItemParametersException extends RuntimeException {
    public InvalidTodoItemParametersException(String msg) {
        super(msg);
    }
}
