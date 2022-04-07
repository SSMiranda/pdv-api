package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;
import com.mirandasidney.pdv.api.domain.Module;
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
            return ResponseEntity.badRequest().build();
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

//    @Override
//    public ResponseEntity<ProfileResponse> findProfileById(Long id) {
//        return repository.findById(id)
//                .map(profile -> ResponseEntity.ok().body(mapper.toDto(profile)))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @Override
//    public ResponseEntity<Void> removeProfile(Long id) {
//        Optional<Profile> profile = repository.findById(id);
//        if (profile.isPresent() && profile.get().getUsers().isEmpty()) {
//            repository.delete(profile.get());
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @Override
//    public ResponseEntity<ProfileResponse> update(ProfileRequest categoryRequest, Long id) {
//        return repository.findById(id)
//                .map(category -> {
//                    BeanUtils.copyProperties(categoryRequest, category);
//                    repository.save(category);
//                    return ResponseEntity.ok().body(mapper.toDto(category));
//                }).orElse(ResponseEntity.badRequest().build());
//    }

}
