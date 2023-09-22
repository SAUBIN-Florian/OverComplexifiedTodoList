package dev.florian.todolistservice.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.florian.todolistservice.dtos.TodolistDto;
import dev.florian.todolistservice.models.Todolist;
import dev.florian.todolistservice.services.TodolistService;

@RestController
@CrossOrigin("*")
@RequestMapping("/todolists")
public class TodolistController {
    
    private TodolistService todolistService;

    public TodolistController(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    @GetMapping("")
    public List<Todolist> allTodoList() {
        return this.todolistService.findAll();
    }

    @GetMapping("/{id}")
    public Todolist todolistById(@PathVariable UUID id) {
        return this.todolistService.findById(id);
    }

    @PostMapping("")
    public void saveTodolist(@RequestBody TodolistDto todolistDto) {
        this.todolistService.save(todolistDto);
    }

    @PutMapping("/{id}")
    public void updateTodolist(@PathVariable UUID id, @RequestBody TodolistDto todolistDto) {
        this.todolistService.update(id, todolistDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTodolist(@PathVariable UUID id) {
        this.todolistService.deleteById(id);
    }
}
