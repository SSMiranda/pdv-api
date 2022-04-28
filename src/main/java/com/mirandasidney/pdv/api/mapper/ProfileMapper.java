package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponseAllAttribute;
import com.mirandasidney.pdv.api.domain.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    Profile toModel(ProfileRequest profile);

    ProfileResponse toDto(Profile profile);

    ProfileResponseAllAttribute toDtoFull(Profile profile);

    Set<ProfileResponseAllAttribute> toProfileListDto(Set<Profile> profiles);

}
