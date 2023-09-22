package dev.florian.todolistservice.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Integer id;
    private String task;
    private Boolean done;
    private UUID todolistId;
}
