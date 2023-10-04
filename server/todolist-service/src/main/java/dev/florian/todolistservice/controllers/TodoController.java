package dev.florian.todolistservice.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.florian.todolistservice.dtos.TodoDto;
import dev.florian.todolistservice.models.Todo;
import dev.florian.todolistservice.services.TodoService;

@RestController
@RequestMapping("todolists/{todolistId}/todos")
public class TodoController {
    
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public List<Todo> findAllTodos(@PathVariable(name="todolistId") UUID todolistId) {
        return this.todoService.findAllTodoByListId();
    }

    @PostMapping("")
    public void saveTodo(@PathVariable(name="todolistId") UUID todolistId, @RequestBody TodoDto todo) {
        this.todoService.save(todolistId, todo);
    }

    @PatchMapping("/{id}")
    public void updateTodo(@PathVariable(name="todolistId") UUID todolistId, @RequestBody TodoDto todo, @PathVariable Integer id) {
        this.todoService.update(todo, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable(name="todolistId") UUID todolistId, @PathVariable Integer id) {
        this.todoService.delete(todolistId, id);
    }
    
}
