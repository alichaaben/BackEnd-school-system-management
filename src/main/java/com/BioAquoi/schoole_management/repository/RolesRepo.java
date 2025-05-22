package com.BioAquoi.schoole_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BioAquoi.schoole_management.entity.Role;

@Repository
public interface RolesRepo extends JpaRepository <Role,Long>{
    
    Optional<Role> findByRoleName(String roleName);

}
