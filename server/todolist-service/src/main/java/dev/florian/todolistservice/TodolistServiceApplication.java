package dev.florian.todolistservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import dev.florian.todolistservice.dtos.TodolistDto;
import dev.florian.todolistservice.services.TodolistService;


@EnableDiscoveryClient
@SpringBootApplication
public class TodolistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner setup(TodolistService tdServ) {
		return args -> {
			tdServ.save(new TodolistDto(null, "setup1", "#CCCCCC"));
			System.out.println("-----------------Setup ok-------------------");
		};
	}
}
