package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.payload.request.role.RoleRequest;
import com.mirandasidney.pdv.api.controller.payload.response.role.RoleResponse;
import com.mirandasidney.pdv.api.service.interfaces.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "AuthorityControler", description = "REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/profiles")
public class AuthorityController {

    private final IRoleService service;

    @Operation(description = "Cadastra um perfil")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> save(@Valid @RequestBody final RoleRequest role) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/profiles/{role}")
                .buildAndExpand(role.getName())
                .toUri();
        RoleResponse response = service.save(role);
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(description = "Busca um perfil pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> findProfileById(@PathVariable final String id) {
        RoleResponse role = service.findProfileById(UUID.fromString(id));

        return ResponseEntity.ok().body(role);
    }

    @Operation(description = "Retorna a lista de perfis cadastrados")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<RoleResponse>> findAll() {

        Set<RoleResponse> roles = service.findAll();
        if(roles.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(roles);
    }

    @Operation(description = "Remove um perfil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable final UUID id) {
        service.removeProfile(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Atualiza dados parciais de um perfil")
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> update(@RequestBody final RoleRequest role, @PathVariable("id") final UUID id)  {
        RoleResponse updated = service.update(role, id);

        return ResponseEntity.ok().body(updated);
    }

}
