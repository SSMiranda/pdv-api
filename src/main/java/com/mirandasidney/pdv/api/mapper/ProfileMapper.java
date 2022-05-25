package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.role.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponseAllAttribute;
import com.mirandasidney.pdv.api.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    Role toModel(ProfileRequest role);

    ProfileResponse toDto(Role role);

    ProfileResponseAllAttribute toDtoFull(Role role);

    Set<ProfileResponseAllAttribute> toProfileListDto(Set<Role> roles);

}
