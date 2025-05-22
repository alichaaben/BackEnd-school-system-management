package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.UserDto;
import com.BioAquoi.schoole_management.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.roleName", target = "roleName")
    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);

    @Mapping(target = "role", ignore = true)
    User toEntity(UserDto userDto);

    @Mapping(target = "role", ignore = true)
    void updateEntityFromDto(@MappingTarget User user, UserDto userDto);
}