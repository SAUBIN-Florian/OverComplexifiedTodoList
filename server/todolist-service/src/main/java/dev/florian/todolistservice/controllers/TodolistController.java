package dev.florian.todolistservice.controllers;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.florian.todolistservice.dtos.HttpResponse;
import dev.florian.todolistservice.dtos.TodolistDto;
import dev.florian.todolistservice.services.TodolistService;

@RestController
@RequestMapping("/todolists")
public class TodolistController {
    
    private TodolistService todolistService;

    public TodolistController(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    @GetMapping("")
    public ResponseEntity<HttpResponse> allTodoList() {
        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("List of todolist")
            .data(Map.of("value", this.todolistService.findAll()))
            .build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> todolistById(@PathVariable UUID id) {
        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.OK.value())
            .status(HttpStatus.OK)
            .message("Todolist with id: " + id)
            .data(Map.of("value", this.todolistService.findById(id)))
            .build();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<HttpResponse> saveTodolist(@RequestBody TodolistDto todolistDto) {
        this.todolistService.save(todolistDto);

        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.ACCEPTED.value())
            .status(HttpStatus.ACCEPTED)
            .message(todolistDto.getTitle() + " saved successfully")
            .data(Map.of("value", todolistDto))
            .build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateTodolist(@PathVariable UUID id, @RequestBody TodolistDto todolistDto) {
        this.todolistService.update(id, todolistDto);

        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.ACCEPTED.value())
            .status(HttpStatus.ACCEPTED)
            .message(todolistDto.getTitle() + " updated successfully")
            .data(Map.of("value", todolistDto))
            .build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteTodolist(@PathVariable UUID id) {
        this.todolistService.deleteById(id);

        HttpResponse response = HttpResponse.builder()
            .timestamp(new Date().toString())
            .statusCode(HttpStatus.ACCEPTED.value())
            .status(HttpStatus.ACCEPTED)
            .message("Todolist with id: " + id + " removed successfully")
            .data(Map.of("value", id))
            .build();

        return ResponseEntity.ok().body(response);
    }
}
