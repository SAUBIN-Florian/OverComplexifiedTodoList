package dev.florian.todolistservice.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todolist {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String title;
    @OneToMany(mappedBy="todolist", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
    @Builder.Default
    private List<Todo> todos = new ArrayList<>();
}
