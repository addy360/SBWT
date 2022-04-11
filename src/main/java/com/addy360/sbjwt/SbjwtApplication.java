package com.addy360.sbjwt;

import com.addy360.sbjwt.models.RoleEntity;
import com.addy360.sbjwt.models.UserEntity;
import com.addy360.sbjwt.services.RoleService;
import com.addy360.sbjwt.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SbjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbjwtApplication.class, args);
	}

	@Bean
	CommandLineRunner run (RoleService roleService, UserService userService){
		return args -> {
			List.of("USER","ADMIN","MANAGER","SUPER_ADMIN").stream().forEach(s -> {
				RoleEntity role = new RoleEntity(null,s);
				roleService.store(role);
			});

			userService.store(new UserEntity(null,"Jane","Doe","jdoe","jdoe@email.com","password",new ArrayList<>()));
		};
	}

}