package com.springboot.example.springbootdemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class SpringbootdemoprojectApplication {
	
	@RequestMapping("/test1")
	public String indexPage() {
		return "Assalamualaikum wrwb";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoprojectApplication.class, args);
	}

}
