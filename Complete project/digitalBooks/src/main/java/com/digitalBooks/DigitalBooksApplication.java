package com.digitalBooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class DigitalBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBooksApplication.class, args);
	}

}
