package com.example.studentmanagament;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentmanagamentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagamentApplication.class, args);
	}

}
