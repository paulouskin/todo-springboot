package pl.paulouski.todospringboot.todo.item.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.paulouski.todospringboot.todo.item.services.TodoItemService;

@SpringBootTest
@Transactional
public class TodoItemDAOTest {

    @Autowired
    private JdbcTodoItemDAO dao;

    @Autowired
    private TodoItemService service;

    @Test
    public void shouldSaveAndFindItemById() {
        var item = service.create("Item 1", "Description 1");
        dao.save(item);
        var itemFromDb = dao.findById(item.getId());
        Assertions.assertEquals(item.getTitle(), itemFromDb.get().getTitle());
    }
}
