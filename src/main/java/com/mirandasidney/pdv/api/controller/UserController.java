package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.payload.request.user.SignupRequest;
import com.mirandasidney.pdv.api.controller.payload.request.user.UpdateUserRequest;
import com.mirandasidney.pdv.api.controller.payload.response.user.UserResponse;
import com.mirandasidney.pdv.api.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "UserController", description = "REST API SISTEMA PDV")
@SecurityRequirement(name = "apipdv")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService service;

    @Operation(summary = "Cadastro de usuários", description = "Cadastra um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Status retornado ao criar um usuário com sucesso.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Status retornado ao efetuar um cadastro mal sucedido",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> save(@Valid @RequestBody final SignupRequest user) {
        return service.save(user);
    }

    @Operation(description = "Busca um usuário pelo ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> findUserById(@PathVariable final UUID id) {
        return service.findUserById(id);
    }

    @Operation(description = "Busca um usuário pelo nome do usuário")
    @GetMapping(value = "/params", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkByUsername(@RequestParam final String username) {
        return service.checkByUsername(username);
    }

    @Secured("ROLE_ADMIN")
    @Operation(summary = "Lista de usuários", description = "Retorna a lista paginada de usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários encontrados",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))})
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return service.findAll(page, size);
    }


    @Operation(description = "Remove um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable final UUID id) {
        return service.removeUser(id);
    }


    @Operation(description = "Atualiza os dados do usuário")
    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> update(@PathVariable("id") final UUID id, @RequestBody final UpdateUserRequest userUpdate) {
        return service.update(userUpdate, id);
    }


//    @Operation(description = "Atualiza parcialmente os dados do usuário")
//    @PatchMapping(value = "/{id}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserResponse> userPartlyUpdate(@PathVariable("id") final String id, @RequestBody final UpdateUserRequest userUpdate) {
//        return service.userPartlyUpdate(userUpdate, UUID.fromString(id));
//    }
}
