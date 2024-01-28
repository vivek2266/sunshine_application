package com.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "SUNSHINE SERVICE", version = "v 3.0", description = "Sunshine API Crud operation"))

public class SunshineExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunshineExampleApplication.class, args);
	}

}