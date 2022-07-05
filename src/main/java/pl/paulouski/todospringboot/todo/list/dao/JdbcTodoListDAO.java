package pl.paulouski.todospringboot.todo.list.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import pl.paulouski.todospringboot.todo.item.dao.JdbcTodoItemDAO;
import pl.paulouski.todospringboot.todo.list.models.TodoList;

import java.util.*;

@Repository
public class JdbcTodoListDAO implements TodoListDAO {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert listInsert;

    private final JdbcTodoItemDAO itemDAO;

    private final RowMapper<TodoList> listMapper = (rs, rowNum) -> new TodoList(
            rs.getString("id"), rs.getString("title")
    );

    public JdbcTodoListDAO(JdbcTemplate jdbcTemplate, JdbcTodoItemDAO itemDAO) {
        this.jdbcTemplate = jdbcTemplate;
        listInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("lists");
        this.itemDAO = itemDAO;
    }

    @Override
    public String save(TodoList list) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", list.getId());
        params.put("title", list.getTitle());
        listInsert.execute(params);
        list.getItems().forEach(itemDAO::save);
        return list.getId();
    }

    @Override
    public Optional<TodoList> findById(String id) {
        if (!existById(id)) return Optional.empty();
        var items = itemDAO.getItemsForList(id);
        var list = jdbcTemplate.queryForObject("select * from lists where id=?", listMapper, id);
        Objects.requireNonNull(list).addAll(items);
        return Optional.of(list);
    }

    @Override
    public List<TodoList> findAll() {
        return jdbcTemplate.query("select * from lists", listMapper);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(TodoList list) {

    }

    @Override
    public boolean existById(String id) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject("select exists(select 1 from lists where id=?)",
                Boolean.class, id));
    }
}
