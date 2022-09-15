package com.mirandasidney.pdv.api.mapper;

import com.mirandasidney.pdv.api.controller.payload.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.payload.response.module.ModuleResponse;
import com.mirandasidney.pdv.api.entities.Module;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    Module toDomain(ModuleRequest dto);

    ModuleResponse toDto(Module module);

    Set<ModuleResponse> toDto(List<Module> module);

}
