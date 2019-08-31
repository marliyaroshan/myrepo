package com.springboot.example.springbootdemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"com.springboot.example.springbootdemoproject,com.springboot.example.springbootdemoproject.service,com.springboot.example.springbootdemoproject.exception" })
public class StudentsExaminationResultsApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentsExaminationResultsApplication.class, args);
	}
}
