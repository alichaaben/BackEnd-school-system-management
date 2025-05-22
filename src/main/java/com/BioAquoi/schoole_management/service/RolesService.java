package com.BioAquoi.schoole_management.service;

import java.util.List;
import java.util.Optional;

import com.BioAquoi.schoole_management.entity.Role;

public interface RolesService {

    Role findById(Long id);

    List<Role> findAll();

    Optional<Role> findByRoleName(String roleName);

    Role insert(Role Entity);

    Role update(Role Entity);

    void deleteById(Long id);

}
