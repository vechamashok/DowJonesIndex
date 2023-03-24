package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.example.demo.*")
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo.entity")
public class DowJonesIndexApplication {

	public static void main(String[] args) {
		SpringApplication.run(DowJonesIndexApplication.class, args);
	}

}
