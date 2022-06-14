package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.dto.request.role.RoleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.role.RoleResponse;
import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponseAllAttribute;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

public interface IProfileService {

    @Transactional
    ResponseEntity<RoleResponse> save(RoleRequest role);

    ResponseEntity<Set<ProfileResponseAllAttribute>> findAll();

    ResponseEntity<ProfileResponseAllAttribute> findProfileById(UUID id);

    @Transactional
    ResponseEntity<Object> removeProfile(UUID id);

    @Transactional
    ResponseEntity<ProfileResponseAllAttribute> update(RoleRequest role, UUID id);
}
