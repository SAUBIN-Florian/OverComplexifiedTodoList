package dev.florian.todolistservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import dev.florian.todolistservice.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    
}
