package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.UserDto;
import com.BioAquoi.schoole_management.entity.User;
import com.BioAquoi.schoole_management.service.UserService;
import com.BioAquoi.schoole_management.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

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
        return ResponseEntity.ok(userMapper.toDto(userService.create(userMapper.toEntity(userDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userService.findById(id);
        userMapper.updateEntityFromDto(user, userDto);
        return ResponseEntity.ok(userMapper.toDto(userService.update(user)));
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