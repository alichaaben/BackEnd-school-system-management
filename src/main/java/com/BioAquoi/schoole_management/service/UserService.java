package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.User;
import java.util.List;

public interface UserService {

    User findById(Long id);
    List<User> findAll();
    User findByUsername(String username);
    List<User> findByRole(String roleName);
    User create(User user);
    User update(User user);
    void delete(Long id);
    boolean usernameExists(String username);
    boolean emailExists(String email);
    User toggleMfa(Long userId);
}
