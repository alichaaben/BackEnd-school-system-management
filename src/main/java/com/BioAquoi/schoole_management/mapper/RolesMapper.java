package com.BioAquoi.schoole_management.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.BioAquoi.schoole_management.dto.RolesDto;
import com.BioAquoi.schoole_management.entity.Role;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RolesMapper {

    @Mapping(source = "roleName", target = "roleName")
    RolesDto map(Role entity);


    
    List<RolesDto> map(List<Role> entities);

    
    @Mapping(source = "roleName", target = "roleName")
    @Mapping(target = "users", ignore = true)
    Role unMap(RolesDto dto);

    @Mapping(source = "roleName", target = "roleName")
    @Mapping(target = "users", ignore = true)
    void updateEntityFromDto(@MappingTarget Role entity, RolesDto dto);

}
