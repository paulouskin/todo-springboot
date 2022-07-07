package pl.paulouski.todospringboot.todo.list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;
import pl.paulouski.todospringboot.todo.list.dao.JdbcTodoListDAO;
import pl.paulouski.todospringboot.todo.list.exceptions.TodoListNotFoundException;
import pl.paulouski.todospringboot.todo.list.models.DeleteResponse;
import pl.paulouski.todospringboot.todo.list.models.TodoList;

@Service
public class TodoListService {
    @Autowired
    JdbcTodoListDAO listDAO;
    @Autowired
    TodoItemService itemService;

    public TodoListService(JdbcTodoListDAO listDAO) {
        this.listDAO = listDAO;
    }

    public JdbcTodoListDAO getListDAO() {
        return listDAO;
    }

    public void setListDAO(JdbcTodoListDAO listDAO) {
        this.listDAO = listDAO;
    }

    public TodoList createList(String title) {
        return new TodoList(title);
    }

    public TodoList getList(String listId) {
        var list =  listDAO.findById(listId).orElseThrow(TodoListNotFoundException::new);
        list.addAll(itemService.getItemsForList(listId));
        return list;
    }

    public String save(TodoList list) {
        String listId = listDAO.save(list);
        list.getItems().forEach(itemService::save);
        return listId;
    }

    public Iterable<TodoList> getAllLists() {
        return listDAO.findAll();
    }

    public DeleteResponse delete(String listId) {
        itemService.getItemsForList(listId).forEach(itemService::delete);
        listDAO.deleteById(listId);
        return new DeleteResponse(String.format("List with Id '%s' have been deleted", listId));
    }
}
