package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.dto.response.UserResponse;
import com.mirandasidney.pdv.api.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@Api("REST API SISTEMA PDV")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @ApiOperation(value = "Cadastra um usuário")
    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody final UserPostRequestBody user) {
        return userService.save(user);
    }

    @ApiOperation(value = "Busca um usuário pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable final Long id) {
        return userService.findUserById(id);
    }

    @ApiOperation(value = "Remove um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final Long id) {
        return userService.removeUser(id);
    }
}
