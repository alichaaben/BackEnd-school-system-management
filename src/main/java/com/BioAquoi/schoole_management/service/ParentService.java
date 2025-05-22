package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Parent;
import java.util.List;

public interface ParentService {
    Parent createParent(Parent parent);
    Parent updateParent(Parent parent);
    Parent getParentById(Long id);
    Parent getParentByUserId(Long userId);
    List<Parent> getAllParents();
    void deleteParent(Long id);
    boolean parentUserExists(Long userId);
}