package com.mirandasidney.pdv.api.service.interfaces;

import com.mirandasidney.pdv.api.controller.payload.request.role.RoleRequest;
import com.mirandasidney.pdv.api.controller.payload.response.role.RoleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

public interface IRoleService {

    @Transactional
    RoleResponse save(RoleRequest role);

    Set<RoleResponse> findAll();

    RoleResponse findProfileById(UUID id);

    @Transactional
    void removeProfile(UUID id);

    @Transactional
    RoleResponse update(RoleRequest role, UUID id);
}
