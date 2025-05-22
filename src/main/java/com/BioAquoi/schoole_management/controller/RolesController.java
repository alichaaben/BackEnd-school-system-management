package com.BioAquoi.schoole_management.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.BioAquoi.schoole_management.Exceptions.ResourceNotFoundException;
import com.BioAquoi.schoole_management.dto.RolesDto;
import com.BioAquoi.schoole_management.entity.Role;
import com.BioAquoi.schoole_management.mapper.RolesMapper;
import com.BioAquoi.schoole_management.service.RolesService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RolesController {
    
    private final RolesService rolesService;
    private final RolesMapper rolesMapper;

    @GetMapping("/{id}")
    public ResponseEntity<RolesDto> findById(@PathVariable Long id) {
        Role entity = rolesService.findById(id);
        RolesDto roleDto = rolesMapper.map(entity);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping()
    public ResponseEntity<List<RolesDto>> findAll() {
        List<Role> entities = rolesService.findAll();
        List<RolesDto> roleDto = rolesMapper.map(entities);
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping()
    public ResponseEntity<RolesDto> insert(@RequestBody RolesDto dto) {
        Optional<Role> existingRole = rolesService.findByRoleName(dto.getRoleName());
        if (existingRole.isPresent()) {
            throw new ResourceNotFoundException("Role already exists with name: " + dto.getRoleName());
        }
    
        Role role = rolesMapper.unMap(dto);
        Role entity = rolesService.insert(role);
        RolesDto rdto = rolesMapper.map(entity);
    
        return ResponseEntity.ok(rdto);
    }
    

    @PutMapping()
    public ResponseEntity<RolesDto> update(@RequestBody RolesDto dto) {

        Role currentRoles = rolesService.findById(dto.getId());
        rolesMapper.updateEntityFromDto(currentRoles, dto);
        Role updatedRoles = rolesService.update(currentRoles);
        RolesDto responseDto = rolesMapper.map(updatedRoles);

        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        rolesService.deleteById(id);
    }
}
