package com.adrianwieczorek.contactdirectoryservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Contact Directory Service API",
				version = "0.0.1",
				description = "REST API for managing contacts (name, phone, email)."
		)
)
@SpringBootApplication
public class ContactDirectoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactDirectoryServiceApplication.class, args);
	}

}
