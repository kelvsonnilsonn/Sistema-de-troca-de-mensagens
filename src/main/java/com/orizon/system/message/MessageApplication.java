package com.orizon.system.message;

import com.orizon.system.message.application.ApplicationRunner;
import com.orizon.system.message.domain.ports.services.MessageService;
import com.orizon.system.message.domain.ports.services.UserService;
import com.orizon.system.message.repositories.UserRepository;
import com.orizon.system.message.services.MessageServiceImpl;
import com.orizon.system.message.services.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationRunner runner){
		return args -> {
			// This method can be used to execute code after the application has started.
			// For example, you can initialize some data or perform some checks.
			System.out.println("Message Application has started successfully.");
			runner.start();
		};
	}
}
