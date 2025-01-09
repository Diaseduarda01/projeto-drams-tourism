package com.tcc.dreams.newdreams;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tcc.dreams.newdreams.model.Role;
import com.tcc.dreams.newdreams.model.service.UserService;
import com.tcc.dreams.newdreams.repository.RoleRepository;



@SpringBootApplication
public class NewdreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewdreamsApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService, RoleRepository roleRepository) {
		return args -> {

		if(roleRepository.findAll().size()  == 0) {
			
			userService.saveRole(new Role("ROLE_USER"));
			userService.saveRole(new Role("ROLE_ADMIN"));
			
			
		}


		};
	}
}
