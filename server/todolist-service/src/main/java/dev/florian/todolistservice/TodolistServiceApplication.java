package dev.florian.todolistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class TodolistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistServiceApplication.class, args);
	}
}
