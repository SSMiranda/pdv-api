package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.payload.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.payload.response.module.ModuleResponse;
import com.mirandasidney.pdv.api.entities.Module;
import com.mirandasidney.pdv.api.exception.ValidationException;
import com.mirandasidney.pdv.api.mapper.ModuleMapper;
import com.mirandasidney.pdv.api.repository.ModuleRepository;
import com.mirandasidney.pdv.api.service.interfaces.IModuleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ModuleServiceImpl implements IModuleService {

    private static final ModuleMapper mapper = ModuleMapper.INSTANCE;
    private final ModuleRepository repository;

    @Override
    public ResponseEntity<ModuleResponse> save(ModuleRequest newModule) {
        final Optional<Module> response = repository.findByName(newModule.getName());
        if(response.isPresent()) {
            throw new ValidationException("Module already exist with name: " + newModule.getName());
        }
        Module module = mapper.toDomain(newModule);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/modules/{name}")
                .buildAndExpand(module.getName())
                .toUri();
        return ResponseEntity.created(uri).body(mapper.toDto(repository.save(module)));
    }

    @Override
    public ResponseEntity<Set<ModuleResponse>> listAllModules() {
        final List<Module> modules = repository.findAll();
        if(modules.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(mapper.toDto(modules));
    }
}
