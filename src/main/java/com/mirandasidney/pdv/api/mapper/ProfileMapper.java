package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.role.RoleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.role.RoleResponse;
import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponseAllAttribute;
import com.mirandasidney.pdv.api.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    Role toModel(RoleRequest role);

    RoleResponse toDto(Role role);

    ProfileResponseAllAttribute toDtoFull(Role role);

    Set<ProfileResponseAllAttribute> toProfileListDto(Set<Role> roles);

}
