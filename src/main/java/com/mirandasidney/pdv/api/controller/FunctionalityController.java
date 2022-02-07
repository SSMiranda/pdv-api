package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
import com.mirandasidney.pdv.api.service.interfaces.IFunctionalityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/functionalities")
public class FunctionalityController {

    private final IFunctionalityService service;

    @ApiOperation(value = "Cadastra uma funcionalidade do software")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FunctionalityResponse> save(@Valid @RequestBody final FunctionalityRequest functionality) {
        return service.save(functionality);
    }

    @ApiOperation(value = "Exibe a lista das funcionalidades do sistema")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<FunctionalityResponse>> listAllFunctionality() {
        return service.listAllFunctionality();
    }

    @ApiOperation(value = "Busca uma funcionalidade pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FunctionalityResponse> findUserById(@PathVariable final Long id) {
        return service.findFunctionalityById(id);
    }
}
