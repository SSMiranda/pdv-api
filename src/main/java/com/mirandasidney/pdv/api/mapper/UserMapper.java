package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.domain.User;
import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "profile", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
    })
    User toUser(UserPostRequestBody userPostRequestBody);

    @InheritInverseConfiguration
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponse(List<User> user);
}
