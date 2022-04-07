package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;
import com.mirandasidney.pdv.api.service.interfaces.IModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/modules")
public class ModuleController {

    private final IModuleService service;

    @ApiOperation(value = "Cadastra um módulo")
    @PostMapping(name = "Cadastra um novo módulo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModuleResponse> save(@Valid @RequestBody final ModuleRequest module) {
        return service.save(module);
    }

    @ApiOperation(value = "Exibe a lista dos módulos do sistema")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ModuleResponse>> listAllModules() {
        return service.listAllModules();
    }

//    @ApiOperation(value = "Busca um usuário pelo ID")
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserResponse> findUserById(@PathVariable final Long id) {
//        return service.findUserById(id);
//    }
//
//    @ApiOperation(value = "Retorna a lista paginada de usuários cadastrados")
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<UserResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
//                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
//        return service.findAll(page, size);
//    }
//
//    @ApiOperation(value = "Remove um usuário")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> remove(@PathVariable final Long id) {
//        return service.removeUser(id);
//    }
//
//    @ApiOperation(value = "Atualiza os dados do usuário")
//    @PutMapping(value = "/{id}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserResponse> update(@PathVariable("id") final Long id, @RequestBody final UserPutRequest userUpdate) {
//        return service.update(userUpdate, id);
//    }
}
