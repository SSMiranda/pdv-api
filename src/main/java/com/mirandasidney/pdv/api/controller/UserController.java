package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.dto.request.UserPostRequestBody;
import com.mirandasidney.pdv.api.dto.response.UserResponse;
import com.mirandasidney.pdv.api.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Api("REST API SISTEMA PDV")
@RequestMapping("/api/v1/users")
public class UserController {

    private UserServiceImpl userService;

    @ApiOperation(value = "Cadastra um usuário")
    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserPostRequestBody user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}
