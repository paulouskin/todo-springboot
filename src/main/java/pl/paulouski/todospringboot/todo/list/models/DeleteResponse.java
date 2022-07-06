package pl.paulouski.todospringboot.todo.list.models;

public class DeleteResponse {

    private String message;

    public DeleteResponse() {
    }

    public DeleteResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
