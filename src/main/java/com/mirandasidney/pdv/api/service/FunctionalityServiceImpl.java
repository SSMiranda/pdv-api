package com.mirandasidney.pdv.api.service;

import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.domain.Functionality;
import com.mirandasidney.pdv.api.domain.Module;
import com.mirandasidney.pdv.api.mapper.FunctionalityMapper;
import com.mirandasidney.pdv.api.repository.FunctionalityRepository;
import com.mirandasidney.pdv.api.repository.ModuleRepository;
import com.mirandasidney.pdv.api.service.interfaces.IFunctionalityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FunctionalityServiceImpl implements IFunctionalityService {

    private static final FunctionalityMapper mapper = FunctionalityMapper.INSTANCE;
    private final FunctionalityRepository repository;
    private final ModuleRepository moduleRepository;

    @Override
    public FunctionalityResponse save(FunctionalityRequest functionalityRequest) {
        Optional<Module> module = moduleRepository.findById((functionalityRequest.getModule().getId()));
        if(module.isPresent()){
            Functionality functionality = mapper.toDomain(functionalityRequest);
            functionality.setModule(module.get());
            return mapper.toDto(repository.save(functionality));
        }
        return null;
    }

    @Override
    public Set<FunctionalityResponse> listAllFunctionality() {
        return mapper.toDto(repository.findAllSet());
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
