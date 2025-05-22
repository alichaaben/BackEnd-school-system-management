package com.BioAquoi.schoole_management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.BioAquoi.schoole_management.entity.Role;
import com.BioAquoi.schoole_management.entity.User;
import com.BioAquoi.schoole_management.repository.RolesRepo;
import com.BioAquoi.schoole_management.repository.UserRepo;

import lombok.RequiredArgsConstructor;

import java.util.*;

@SpringBootApplication
@RequiredArgsConstructor
public class SchooleManagementApplication {

	private final UserRepo userRepository;
	private final RolesRepo rolesRepo;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SchooleManagementApplication.class, args);
	}

		@Bean
	public CommandLineRunner initDatabase() {
		return args -> {
			Optional<Role> adminRoleOpt = rolesRepo.findByRoleName("ROLE_Admin");
			Role adminRole = adminRoleOpt.orElseGet(() -> {
				Role newRole = new Role();
				newRole.setRoleName("ROLE_Admin");
				return rolesRepo.save(newRole);
			});

			
			Optional<User> existingAdminOpt = userRepository.findByUsername("Admin");
			User existingAdmin = existingAdminOpt.orElse(null);
			if (existingAdmin == null) {
				User adminUser = new User();
				adminUser.setUsername("Admin");
				adminUser.setEmail("admin@gmail.com");
				adminUser.setPassword(passwordEncoder.encode("talel1234"));
				adminUser.setRole(adminRole);
				adminUser.setMfaEnabled(false);


				userRepository.save(adminUser);
				System.out.println("Admin user created successfully");
			} else {
				System.out.println("Admin user already exists, skipping creation.");
			}

		};
	}

}
