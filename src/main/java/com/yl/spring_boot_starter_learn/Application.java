package com.yl.spring_boot_starter_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application{
	
	
	
	@RequestMapping("/say")
	public String say() {
		return "hello world!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
