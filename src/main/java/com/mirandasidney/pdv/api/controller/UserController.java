package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.dto.request.user.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.dto.response.user.UserResponse;
import com.mirandasidney.pdv.api.service.interfaces.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService service;

    @ApiOperation(value = "Cadastra um usuário")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> save(@Valid @RequestBody final UserPostRequestBody user) {

        return service.save(user);
    }

    @ApiOperation(value = "Busca um usuário pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> findUserById(@PathVariable final UUID id) {
        return service.findUserById(id);
    }

    @ApiOperation(value = "Retorna a lista paginada de usuários cadastrados")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return service.findAll(page, size);
    }

    @ApiOperation(value = "Remove um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final UUID id) {
        return service.removeUser(id);
    }

    @ApiOperation(value = "Atualiza os dados do usuário")
    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> update(@PathVariable("id") final UUID id, @RequestBody final UpdateUserRequest userUpdate) {
        return service.update(userUpdate, id);
    }

    @ApiOperation(value = "Atualiza parcialmente os dados do usuário")
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> userPartlyUpdate(@PathVariable("id") final UUID id, @RequestBody final UpdateUserRequest userUpdate) {
        return service.userPartlyUpdate(userUpdate, id);
    }
}
