package ru.hashfactory.empty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude={org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class} )
public class EmptyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmptyApplication.class, args);
	}
}
