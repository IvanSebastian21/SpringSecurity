package com.app.SpringSucurityJWT;

import com.app.SpringSucurityJWT.models.EnumRole;
import com.app.SpringSucurityJWT.models.RoleEntity;
import com.app.SpringSucurityJWT.models.UserEntity;
import com.app.SpringSucurityJWT.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SpringSucurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSucurityJwtApplication.class, args);
	}


	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {

			UserEntity userEntity = UserEntity.builder()
					.email("ivansebas@mail.com")
					.username("ivan")
					.password(passwordEncoder.encode("123456"))
					.roles(Set.of(RoleEntity.builder()
							.name(EnumRole.valueOf(EnumRole.USER.name()))
							.build()))
					.build();

			userRepository.save(userEntity);

		};
	}
}
