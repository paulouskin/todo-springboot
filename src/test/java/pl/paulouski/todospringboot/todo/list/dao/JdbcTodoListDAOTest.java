package pl.paulouski.todospringboot.todo.list.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;
import pl.paulouski.todospringboot.todo.list.models.TodoList;
import pl.paulouski.todospringboot.todo.list.services.TodoListService;

@SpringBootTest
@Transactional
public class JdbcTodoListDAOTest {

    @Autowired
    JdbcTodoListDAO dao;

    @Autowired
    TodoListService service;

    @Test
    public void shouldSaveListAndGetItFromDB() {
        TodoList list = service.createList("Test list");
        dao.save(list);
        TodoList listFromDb = dao.findById(list.getId()).get();
        Assertions.assertAll(
                () -> Assertions.assertEquals(list.getTitle(), listFromDb.getTitle())
        );

    }


}
