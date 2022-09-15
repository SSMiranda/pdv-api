package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.payload.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.payload.response.module.ModuleResponse;
import com.mirandasidney.pdv.api.service.interfaces.IModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "ModuleController", description = "REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/modules")
public class ModuleController {

    private final IModuleService service;

    @Operation(description = "Cadastra um módulo")
    @PostMapping(name = "Cadastra um novo módulo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleResponse> save(@Valid @RequestBody final ModuleRequest module) {
        return service.save(module);
    }

    @Operation(description = "Exibe a lista dos módulos do sistema")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ModuleResponse>> listAllModules() {
        return service.listAllModules();
    }

//    @Operation(description = "Busca um usuário pelo ID")
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserResponse> findUserById(@PathVariable final Long id) {
//        return service.findUserById(id);
//    }
//
//    @Operation(description = "Retorna a lista paginada de usuários cadastrados")
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<UserResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
//                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
//        return service.findAll(page, size);
//    }
//
//    @Operation(description = "Remove um usuário")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> remove(@PathVariable final Long id) {
//        return service.removeUser(id);
//    }
//
//    @Operation(description = "Atualiza os dados do usuário")
//    @PutMapping(value = "/{id}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserResponse> update(@PathVariable("id") final Long id, @RequestBody final UserPutRequest userUpdate) {
//        return service.update(userUpdate, id);
//    }
}
