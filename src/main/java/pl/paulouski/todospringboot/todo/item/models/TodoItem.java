package pl.paulouski.todospringboot.todo.item.models;

public class TodoItem implements Toggleable{

    private String title;
    private String description;
    private TodoItemStatus status;

    public TodoItem(String title, String description) {
        this.title = title;
        this.description = description;
        status = TodoItemStatus.PENDING;
    }

    public TodoItem() {
    }

    public static TodoItem of(String title, String description) {
        return new TodoItem(title, description);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TodoItemStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void complete() {
        this.status = TodoItemStatus.COMPLETED;
    }

    @Override
    public void onHold() {
        this.status = TodoItemStatus.PENDING;
    }

    @Override
    public void startProgress() {
        this.status = TodoItemStatus.IN_PROGRESS;
    }
}
