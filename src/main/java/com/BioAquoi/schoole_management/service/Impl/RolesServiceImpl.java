package com.BioAquoi.schoole_management.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.InvalidEntityException;

import com.BioAquoi.schoole_management.entity.Role;
import com.BioAquoi.schoole_management.repository.RolesRepo;
import com.BioAquoi.schoole_management.service.RolesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService{
    private final RolesRepo rolesRepo;
    
    @Override
    public Role findById(Long id) {
        return rolesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + id));
    }

    @Override
    public List<Role> findAll() {
        return rolesRepo.findAll();
    }

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return rolesRepo.findByRoleName(roleName);

    }

    @Override
    public Role insert(Role entity) {
        if (entity.getRoleName() == null || entity.getRoleName().isEmpty()) {
            throw new InvalidEntityException("Role name cannot be empty.");
        }
        return rolesRepo.save(entity);
    }

    @Override
    public Role update(Role entity) {
        Role currentRoles = rolesRepo.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found with ID: " + entity.getId()));
    
        currentRoles.setRoleName(entity.getRoleName());
        return rolesRepo.save(currentRoles);
    }
    

    @Override
    public void deleteById(Long id) {
        rolesRepo.deleteById(id);
    }

}
