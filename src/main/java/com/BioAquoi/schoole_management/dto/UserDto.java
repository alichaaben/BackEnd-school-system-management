package com.BioAquoi.schoole_management.dto;

import lombok.Data;


@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean mfaEnabled;
    private String roleName;
}