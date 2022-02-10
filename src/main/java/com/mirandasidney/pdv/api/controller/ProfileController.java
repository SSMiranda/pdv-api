package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.dto.request.profile.ProfileRequest;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponse;
import com.mirandasidney.pdv.api.controller.dto.response.profile.ProfileResponseWithModules;
import com.mirandasidney.pdv.api.service.interfaces.IProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final IProfileService service;

    @ApiOperation(value = "Cadastra um perfil")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponse> save(@Valid @RequestBody final ProfileRequest profile) {
        return service.save(profile);
    }

    @ApiOperation(value = "Busca um perfil pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponse> findProfileById(@PathVariable final UUID id) {
        return service.findProfileById(id);
    }

    @ApiOperation(value = "Retorna a lista de perfis cadastrados")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ProfileResponse>> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Remove um perfil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable final UUID id) {
        return service.removeProfile(id);
    }

    @ApiOperation(value = "Atualiza dados parciais de um perfil")
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponseWithModules> update(@RequestBody final Map<Object, Object> profile, @PathVariable("id") final UUID id) {
        return service.update(profile, id);
    }

}
