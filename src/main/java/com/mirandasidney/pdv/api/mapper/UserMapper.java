package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserPostRequestBody userPostRequestBody);

    @Mappings({
            @Mapping(source = "userId", target = "userId", resultType = String.class)
    })

    UserResponse toUserResponse(User user);

}
