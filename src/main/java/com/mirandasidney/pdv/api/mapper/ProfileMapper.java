package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfilePostRequest;
import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.domain.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    Profile toModel(ProfilePostRequest profile);

    ProfileResponse toDto(Profile profile);

//    Set<CategoryWithListProductResponse> toCategoryListDto(Set<Category> categories);
//
//    CategoryWithListProductResponse toCategoryListDto(Category category);

}
