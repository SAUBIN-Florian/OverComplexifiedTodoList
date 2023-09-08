package dev.florian.todolistservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import dev.florian.todolistservice.models.Todolist;
import dev.florian.todolistservice.services.TodolistService;

@EnableDiscoveryClient
@SpringBootApplication
public class TodolistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner dbTest(TodolistService service) {
		return args -> {
			service.save(new Todolist(null, "test 1"));
			service.save(new Todolist(null, "test 2"));
			service.save(new Todolist(null, "test 3"));
		};
	}
}
