package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.Parent;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.ParentRepo;
import com.BioAquoi.schoole_management.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepo parentRepo;

    @Override
    public Parent createParent(Parent parent) {
        if (parentRepo.existsByUserId(parent.getUser().getId())) {
            throw new DuplicateEntityException("Parent with this user already exists");
        }
        return parentRepo.save(parent);
    }

    @Override
    public Parent updateParent(Parent parent) {
        Parent existingParent = getParentById(parent.getId());
        // Add update logic here
        return parentRepo.save(existingParent);
    }

    @Override
    public Parent getParentById(Long id) {
        return parentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with ID: " + id));
    }

    @Override
    public Parent getParentByUserId(Long userId) {
        return parentRepo.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with user ID: " + userId));
    }

    @Override
    public List<Parent> getAllParents() {
        return parentRepo.findAll();
    }

    @Override
    public void deleteParent(Long id) {
        Parent parent = getParentById(id);
        parentRepo.delete(parent);
    }

    @Override
    public boolean parentUserExists(Long userId) {
        return parentRepo.existsByUserId(userId);
    }
}