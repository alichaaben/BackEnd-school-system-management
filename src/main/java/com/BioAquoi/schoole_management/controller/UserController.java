package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.UserDto;
import com.BioAquoi.schoole_management.entity.Role;
import com.BioAquoi.schoole_management.entity.User;
import com.BioAquoi.schoole_management.service.UserService;
import com.BioAquoi.schoole_management.mapper.UserMapper;
import com.BioAquoi.schoole_management.repository.RolesRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RolesRepo roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userMapper.toDtoList(userService.findAll()));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userMapper.toDto(userService.findByUsername(username)));
    }

    @GetMapping("/role/{roleName}")
    public ResponseEntity<List<UserDto>> getUsersByRole(@PathVariable String roleName) {
        return ResponseEntity.ok(userMapper.toDtoList(userService.findByRole(roleName)));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        // Hachage du mot de passe avec BCrypt
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashedPassword);

        Role role = roleRepository.findByRoleName(userDto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found: " + userDto.getRoleName()));

        user.setRole(role);

        User savedUser = userService.create(user);

        return ResponseEntity.ok(userMapper.toDto(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userService.findById(id);

        userMapper.updateEntityFromDto(user, userDto);

        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            String hashedPassword = passwordEncoder.encode(userDto.getPassword());
            user.setPassword(hashedPassword);
        }

        if (userDto.getRoleName() != null && !userDto.getRoleName().isBlank()) {
            Role role = roleRepository.findByRoleName(userDto.getRoleName())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + userDto.getRoleName()));
            user.setRole(role);
        }

        User updatedUser = userService.update(user);

        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/toggle-mfa")
    public ResponseEntity<UserDto> toggleMfa(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.toggleMfa(id)));
    }
}