package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByRoleRoleName(String roleName);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}