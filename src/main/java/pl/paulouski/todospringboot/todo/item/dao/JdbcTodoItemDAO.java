package pl.paulouski.todospringboot.todo.item.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import pl.paulouski.todospringboot.todo.item.models.TodoItem;
import pl.paulouski.todospringboot.todo.item.models.TodoItemStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTodoItemDAO implements TodoItemDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert itemInsert;

    private final RowMapper<TodoItem> itemMapper = (rs, rowNum) ->
            new TodoItem(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    TodoItemStatus.valueOf(rs.getString("status")),
                    rs.getString("listId")
            );

    @Autowired
    public JdbcTodoItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        itemInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("items");
    }

    @Override
    public void save(TodoItem item) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", item.getId());
        params.put("title", item.getTitle());
        params.put("description", item.getDescription());
        params.put("status", item.getStatus().toString());
        params.put("listId", item.getListId());
        itemInsert.execute(params);
    }

    @Override
    public Optional<TodoItem> findById(String id) {
        if (!existById(id)) return Optional.empty();
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select * from items where id=?", itemMapper, id)
        );
    }

    @Override
    public List<TodoItem> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(TodoItem item) {
        jdbcTemplate.update("delete from items where id=?", item.getId());
    }

    @Override
    public boolean existById(String id) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject("select exists(select 1 from items where id=?)",
                Boolean.class, id));
    }

    public List<TodoItem> getItemsForList(String listId) {
        return jdbcTemplate.query("select * from items where listId=?", itemMapper, listId);
    }
}
