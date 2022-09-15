package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.payload.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.payload.response.functionality.FunctionalityResponse;
import com.mirandasidney.pdv.api.entities.Functionality;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface FunctionalityMapper {
    FunctionalityMapper INSTANCE = Mappers.getMapper(FunctionalityMapper.class);

    Functionality toDomain(FunctionalityRequest dto);

    FunctionalityResponse toDto(Functionality functionality);

    Set<FunctionalityResponse> toDto(Set<Functionality> functionality);
}
