package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
import com.mirandasidney.pdv.api.domain.Functionality;
import com.mirandasidney.pdv.api.domain.Module;
import com.mirandasidney.pdv.api.exception.ResourceNotFoundException;
import com.mirandasidney.pdv.api.mapper.FunctionalityMapper;
import com.mirandasidney.pdv.api.repository.FunctionalityRepository;
import com.mirandasidney.pdv.api.repository.ModuleRepository;
import com.mirandasidney.pdv.api.service.interfaces.IFunctionalityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FunctionalityServiceImpl implements IFunctionalityService {

    private static final FunctionalityMapper mapper = FunctionalityMapper.INSTANCE;
    private final FunctionalityRepository repository;
    private final ModuleRepository moduleRepository;

    @Override
    public ResponseEntity<FunctionalityResponse> save(FunctionalityRequest functionalityRequest) {
        Optional<Module> module = moduleRepository.findById((functionalityRequest.getModule().getId()));
        if (module.isPresent()) {
            Functionality functionality = mapper.toDomain(functionalityRequest);
            functionality.setModule(module.get());
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/api/v1/functionalities/{name}")
                    .buildAndExpand(functionality.getName())
                    .toUri();

            final Functionality saved = repository.save(functionality);

            return ResponseEntity.created(uri).body(mapper.toDto(saved));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Set<FunctionalityResponse>> listAllFunctionality() {
        final Set<Functionality> list = repository.findAllSet();
        if (list.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(mapper.toDto(list));
    }

    @Override
    public ResponseEntity<FunctionalityResponse> findFunctionalityById(UUID id) {
        return this.repository.findById(id)
                .map(functionality -> ResponseEntity.ok().body(mapper.toDto(functionality)))
                .orElseThrow(() -> new ResourceNotFoundException("Functionality not found with UUID: " + id));
    }


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
