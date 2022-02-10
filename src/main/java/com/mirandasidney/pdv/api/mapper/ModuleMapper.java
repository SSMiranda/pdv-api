package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;
import com.mirandasidney.pdv.api.domain.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    Module toDomain(ModuleRequest dto);

    @Mapping(source = "id", target = "id", resultType = String.class)
    ModuleResponse toDto(Module module);

    Set<ModuleResponse> toDto(List<Module> module);

}
