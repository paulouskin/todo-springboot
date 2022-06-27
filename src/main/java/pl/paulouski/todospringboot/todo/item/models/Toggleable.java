package pl.paulouski.todospringboot.todo.item.models;

public interface Toggleable {
    void complete();
    void onHold();
    void startProgress();
}
