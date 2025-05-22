package com.BioAquoi.schoole_management.service.Impl;


import com.BioAquoi.schoole_management.entity.User;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.UserRepo;
import com.BioAquoi.schoole_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
    }

    @Override
    public List<User> findByRole(String roleName) {
        return userRepo.findByRoleRoleName(roleName);
    }

    @Override
    public User create(User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new DuplicateEntityException("Username already exists");
        }
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new DuplicateEntityException("Email already exists");
        }
        return userRepo.save(user);
    }

    @Override
    public User update(User user) {
        User existingUser = findById(user.getId());
        // Add update logic here
        return userRepo.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        userRepo.delete(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public User toggleMfa(Long userId) {
        User user = findById(userId);
        user.setMfaEnabled(!user.isMfaEnabled());
        return userRepo.save(user);
    }
}