package dev.florian.todolistservice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.florian.todolistservice.dtos.TodoDto;
import dev.florian.todolistservice.models.Todo;
import dev.florian.todolistservice.models.Todolist;
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

    public Todo save(UUID todolistId, TodoDto todo) {
        Todo newTodo = Todo.builder()
            .task(todo.getTask())
            .done(false)
            .todolist(this.todolistService.findById(todolistId))
            .build();
        this.todoRepository.save(newTodo);

        return newTodo;
    }

    public Todo update(TodoDto todo, Integer id) {
        if(this.todoRepository.existsById(id)) {
            Todo updateTodo = this.todoRepository.findById(id).get();
            updateTodo.setDone(todo.getDone());

            this.todoRepository.save(updateTodo);

            return updateTodo;
        } else {
            throw new IllegalArgumentException("Todo with id: " + id + "does not exist...");
        }
    }

    public Todo delete(UUID todolistId, Integer id) {
        Todolist actualTodolist = this.todolistService.findById(todolistId);
        Todo todoToDelete = actualTodolist.getTodos().stream().filter(todo -> todo.getId().equals(id)).findFirst().orElse(null);

        if(todoToDelete != null) {
            this.todoRepository.deleteById(id);

            return todoToDelete;
        } else {
            throw new IllegalArgumentException("Todo with id: " + id + "does not exist...");
        }
    }
}
