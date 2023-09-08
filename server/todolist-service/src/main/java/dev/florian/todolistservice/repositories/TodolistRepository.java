package dev.florian.todolistservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.florian.todolistservice.models.Todolist;

public interface TodolistRepository extends JpaRepository<Todolist, UUID> {
    
}
