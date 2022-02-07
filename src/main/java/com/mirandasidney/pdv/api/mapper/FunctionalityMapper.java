package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
import com.mirandasidney.pdv.api.domain.Functionality;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FunctionalityMapper {
    FunctionalityMapper INSTANCE = Mappers.getMapper(FunctionalityMapper.class);

    Functionality toDomain(FunctionalityRequest dto);

    FunctionalityResponse toDto(Functionality functionality);

}
