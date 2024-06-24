package com.starter;


import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.starter")
@EnableJpaRepositories(basePackages = "com.starter.repo")
public class SpringStarterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStarterDemoApplication.class, args);
		String d = "28-02-2023";
		System.out.println(new Date().toInstant().toString());
	}

}
