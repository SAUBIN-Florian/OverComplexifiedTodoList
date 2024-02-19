package dev.florian.todolistservice.dtos;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodolistDto {
    private UUID id;
    private String title;
    private String color;
}
