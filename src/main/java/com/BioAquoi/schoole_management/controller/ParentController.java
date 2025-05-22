package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.ParentDto;
import com.BioAquoi.schoole_management.entity.Parent;
import com.BioAquoi.schoole_management.service.ParentService;
import com.BioAquoi.schoole_management.mapper.ParentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ParentController {

    private final ParentService parentService;
    private final ParentMapper parentMapper;

    @PostMapping
    public ResponseEntity<ParentDto> createParent(@RequestBody ParentDto parentDto) {
        return ResponseEntity.ok(
                parentMapper.toDto(
                        parentService.createParent(
                                parentMapper.toEntity(parentDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentDto> updateParent(
            @PathVariable Long id, 
            @RequestBody ParentDto parentDto) {
        Parent parent = parentService.getParentById(id);
        parentMapper.updateEntityFromDto(parent, parentDto);
        return ResponseEntity.ok(
                parentMapper.toDto(parentService.updateParent(parent))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDto> getParentById(@PathVariable Long id) {
        return ResponseEntity.ok(
                parentMapper.toDto(parentService.getParentById(id))
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ParentDto> getParentByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(
                parentMapper.toDto(parentService.getParentByUserId(userId))
        );
    }

    @GetMapping
    public ResponseEntity<List<ParentDto>> getAllParents() {
        return ResponseEntity.ok(
                parentMapper.toDtoList(parentService.getAllParents())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
