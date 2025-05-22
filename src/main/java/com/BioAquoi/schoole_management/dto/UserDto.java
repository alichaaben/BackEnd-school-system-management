package com.BioAquoi.schoole_management.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean mfaEnabled;
    private LocalDateTime createdAt;
    private String roleName;
}