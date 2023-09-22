package dev.florian.todolistservice.repositories;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.florian.todolistservice.models.Todolist;

@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class TodolistRepositoryTest {

    @Autowired
    private TodolistRepository todolistRepository;

    @Test
    public void TodolistRepositorySave() {

        // Arrange
        Todolist todolist = Todolist.builder().title("test todolist").build();

        // Act
        todolistRepository.save(todolist);

        // Assert
        Assertions.assertThat(todolist).isNotNull();
        Assertions.assertThat(todolist.getId()).isNotNull();
    }

    @Test
    public void TodolistRepositoryFindById() {

        Todolist todolist = Todolist.builder().title("test todolist id").build();
        todolistRepository.save(todolist);

        Assertions.assertThat(todolist.getId()).isNotNull();
        Assertions.assertThat(todolist.getId()).isInstanceOf(UUID.class);
    }

    @Test
    public void TodolistRepositoryReturnMoreThan1Todolist() {

        Todolist todolist1 = Todolist.builder().title("todolist test 1").build();
        Todolist todolist2 = Todolist.builder().title("todolist test 2").build();

        todolistRepository.save(todolist1);
        todolistRepository.save(todolist2);
        List<Todolist> todolists = todolistRepository.findAll();

        Assertions.assertThat(todolists).isNotEmpty();
        Assertions.assertThat(todolists.size()).isEqualTo(2);
    }
}
