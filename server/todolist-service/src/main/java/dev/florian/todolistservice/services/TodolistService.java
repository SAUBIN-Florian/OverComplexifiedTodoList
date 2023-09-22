package dev.florian.todolistservice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.florian.todolistservice.dtos.TodolistDto;
import dev.florian.todolistservice.models.Todolist;
import dev.florian.todolistservice.repositories.TodolistRepository;

@Service
public class TodolistService {
    
    private TodolistRepository listRepository;

    public TodolistService(TodolistRepository repository) {
        this.listRepository = repository;
    }

    public List<Todolist> findAll() {
        return this.listRepository.findAll();
    }

    public Todolist findById(UUID id) {
        Todolist todolist = this.listRepository.findById(id).orElse(null);

        if(todolist != null) {
            return todolist;
        } else {
            throw new IllegalArgumentException("Todolist with UUID " + id + " does not exist..");
        }
    }

    public void save(TodolistDto todolistDto) {
        Todolist todolist = Todolist.builder().title(todolistDto.getTitle()).build();
        this.listRepository.save(todolist);
    }

    public void update(UUID id, TodolistDto todolistDto) {
        Todolist updateTodolist = this.listRepository.findById(id).orElse(null);

        if(updateTodolist != null) {
            if(todolistDto.getTitle() != null) updateTodolist.setTitle(todolistDto.getTitle());
            
            this.listRepository.save(updateTodolist);
        } else {
            throw new IllegalArgumentException("Todolist with UUID " + id + " does not exist..");
        }
    }

    public void deleteById(UUID id) {
        this.listRepository.deleteById(id);
    }
}
