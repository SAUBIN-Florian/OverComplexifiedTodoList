package dev.florian.todolistservice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.florian.todolistservice.models.Todolist;
import dev.florian.todolistservice.repositories.TodolistRepository;

@Service
public class TodolistService {
    
    private TodolistRepository listRepository;

    @Autowired
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

    public void save(Todolist todolist) {
        this.listRepository.save(todolist);
    }

    public void update(UUID id, Todolist todolist) {
        Todolist updateTodolist = this.listRepository.findById(id).orElse(null);

        if(updateTodolist != null) {
            if(todolist.getTitle() != null) updateTodolist.setTitle(todolist.getTitle());
            
            this.listRepository.save(updateTodolist);
        } else {
            throw new IllegalArgumentException("Todolist with UUID " + id + " does not exist..");
        }
    }

    public void deleteById(UUID id) {
        this.listRepository.deleteById(id);
    }
}
