package dev.florian.todolistservice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.florian.todolistservice.dtos.TodoDto;
import dev.florian.todolistservice.models.Todo;
import dev.florian.todolistservice.repositories.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private TodolistService todolistService;

    public TodoService(TodoRepository todoRepository, TodolistService todolistService) {
        this.todoRepository = todoRepository;
        this.todolistService = todolistService;
    }

    public List<Todo> findAllTodoByListId() {
        return this.todoRepository.findAll();
    }

    public void save(UUID todolistId, TodoDto todo) {
        Todo newTodo = new Todo(todo.getId(), todo.getTask(), todo.getDone(), this.todolistService.findById(todolistId));
        this.todoRepository.save(newTodo);
    }

    public void update(TodoDto todo, Integer id) {
        if(this.todoRepository.existsById(id)) {
            Todo updateTodo = this.todoRepository.findById(id).get();
            updateTodo.setDone(todo.getDone());

            this.todoRepository.save(updateTodo);
        } else {
            throw new IllegalArgumentException("Todo with UUID: " + id + "does not exist...");
        }
    }

    public void delete(Integer id) {
        this.todoRepository.deleteById(id);
    }
}
