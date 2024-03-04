package dev.florian.todolistservice.controllers;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.florian.todolistservice.dtos.HttpResponse;
import dev.florian.todolistservice.dtos.TodoDto;
import dev.florian.todolistservice.services.TodoService;

@RestController
@RequestMapping("todolists/{todolistId}/todos")
public class TodoController {
    
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public ResponseEntity<HttpResponse> findAllTodos(@PathVariable(name="todolistId") UUID todolistId) {
        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("List of todos")
            .data(Map.of("value", this.todoService.findAllTodoByListId()))
            .build();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<HttpResponse> saveTodo(@PathVariable(name="todolistId") UUID todolistId, @RequestBody TodoDto todo) {
        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Todo saved successfully")
            .data(Map.of("value", this.todoService.save(todolistId, todo)))
            .build();

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpResponse> updateTodo(@PathVariable(name="todolistId") UUID todolistId, @RequestBody TodoDto todo, @PathVariable Integer id) {
        this.todoService.update(todo, id);

        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Todo with id: " + id + " updated successfully")
            .data(Map.of("value", todo))
            .build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteTodo(@PathVariable(name="todolistId") UUID todolistId, @PathVariable Integer id) {
        this.todoService.delete(todolistId, id);
        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Todo with id: " + id + " deleted successfully")
            .data(Map.of("value", todolistId))
            .build();

        return ResponseEntity.ok().body(response);
    }
    
}
