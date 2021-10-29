package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.domain.User;
import com.mirandasidney.pdv.api.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostRequestBody userPostRequestBody);

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "profile", target = "profile")
    @Mapping(source = "createdAt", target = "createdAt")
    public abstract UserResponse toUserResponse(User user);
}
