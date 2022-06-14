package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.dto.request.role.RoleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.role.ProfileResponseAllAttribute;
import com.mirandasidney.pdv.api.controller.dto.response.role.RoleResponse;
import com.mirandasidney.pdv.api.service.interfaces.IProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "AuthorityControler", description = "REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/profiles")
public class AuthorityController {

    private final IProfileService service;

    @Operation(description = "Cadastra um perfil")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleResponse> save(@Valid @RequestBody final RoleRequest role) {
        return service.save(role);
    }

    @Operation(description = "Busca um perfil pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseAllAttribute> findProfileById(@PathVariable final String id) {
        return service.findProfileById(UUID.fromString(id));
    }

    @Operation(description = "Retorna a lista de perfis cadastrados")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ProfileResponseAllAttribute>> findAll() {
        return service.findAll();
    }

    @Operation(description = "Remove um perfil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable final UUID id) {
        return service.removeProfile(id);
    }

    @Operation(description = "Atualiza dados parciais de um perfil")
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseAllAttribute> update(@RequestBody final RoleRequest role, @PathVariable("id") final UUID id)  {
        return service.update(role, id);
    }

}
