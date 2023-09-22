package dev.florian.todolistservice.repositories;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.florian.todolistservice.models.Todo;

@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    public void init() {
        Todo todo1 = Todo.builder().task("test task 1").done(false).build();
        todoRepository.save(todo1);
    }
    
    @Test
    public void todoRepository_FindAll() {
        List<Todo> todos = todoRepository.findAll();

        Assertions.assertThat(todos).isNotEmpty();
        Assertions.assertThat(todos.size()).isEqualTo(1);
    }

    @Test
    public void todoRepository_FindOne() {

        Optional<Todo> todo = todoRepository.findById(1);
        Optional<Todo> todoNull = todoRepository.findById(2);

        Assertions.assertThat(todo.get()).isNotNull();
        Assertions.assertThat(todo.get().getId()).isEqualTo(1);
        Assertions.assertThat(todoNull).isEqualTo(null);
    }

     @Test
    public void todoRepository_Save_ReturnMoreThanOne() {
        Todo newTodo = Todo.builder().task("new test todo").done(false).build();
        todoRepository.save(newTodo);
        List<Todo> todos = todoRepository.findAll();

        Assertions.assertThat(newTodo).isNotNull();
        Assertions.assertThat(todos.size()).isGreaterThan(1);
        Assertions.assertThat(todos.indexOf(newTodo)).isEqualTo(1);
    }
}
