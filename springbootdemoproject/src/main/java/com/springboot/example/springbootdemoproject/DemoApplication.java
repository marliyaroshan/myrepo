package com.springboot.example.springbootdemoproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	@Value("${spring.application.name:test}")
	String applicationName;
	// to set default value to the property if not present in teh properties file
	@Value("${spring.application.default.name:mock default Application Name}")
	String applicationDefaultName;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping(value = "/test")
	public String hello() {
		logger.info("This is my first Sprint Boot application");
		return applicationName + " This is default name " + applicationDefaultName;
	}

	// Example to read request Param
	@RequestMapping(value = "/appname")
	public String applicationName(
			@RequestParam(value = "appName", required = false, defaultValue = "mock demo") String appName) {
		logger.info("Example to handle the request param");
		return appName;
	}
}