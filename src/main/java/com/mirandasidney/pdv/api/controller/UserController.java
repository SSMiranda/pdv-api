package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.controller.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.controller.dto.request.UserPutRequestByUser;
import com.mirandasidney.pdv.api.controller.dto.response.UserResponse;
import com.mirandasidney.pdv.api.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @ApiOperation(value = "Cadastra um usuário")
    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> save(@Valid @RequestBody final UserPostRequestBody user) {
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}").buildAndExpand(user.getUsername()).toUri();
        return ResponseEntity.created(uri).body(userService.save(user));
    }

    @ApiOperation(value = "Busca um usuário pelo ID")
    @GetMapping(value = "/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> findUserById(@PathVariable final Long id) {
        final UserResponse user = userService.findUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(user);
        }
    }

    @ApiOperation(value = "Remove um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final Long id) {
        return userService.removeUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Atualiza os dados do usuário")
    @PutMapping(value = "/{id}",
            consumes =  MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> update(@PathVariable("id") final Long id, @RequestBody final UserPutRequestByUser userUpdate) {
        return ResponseEntity.ok(userService.update(userUpdate, id));
    }

//    @ApiOperation(value = "Atualiza os dados do usuário a partir de um Admin")
//    @PutMapping(value = "/{id}",
//            consumes =  MediaType.APPLICATION_JSON_VALUE,
//            produces =  MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserResponse> update(@PathVariable final Long id, @RequestBody final UserPutRequestByAdmin userUpdate) {
//        return ResponseEntity.ok(userService.update(userUpdate, id));
//    }
}
