package com.mirandasidney.pdv.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequestById;
import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
import com.mirandasidney.pdv.api.service.interfaces.IFunctionalityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FunctionalityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IFunctionalityService service;

    @Test
    @DisplayName("Must return status code 201 when making POST request to endpoint - /api/v1/functionalities")
    void must_Return201_When_New_Functionality() throws Exception {
        final FunctionalityRequest functionality = new FunctionalityRequest("teste", "teste", true, false, new ModuleRequestById());
        final FunctionalityResponse response = new FunctionalityResponse(1L, functionality.getName(), functionality.getDescription(), functionality.isCanView(), functionality.isCanEdit());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/functionalities/{name}")
                .buildAndExpand(functionality.getName())
                .toUri();
        when(service.save(any(FunctionalityRequest.class))).thenReturn(ResponseEntity.created(uri).body(response));

        mockMvc.perform(post("/api/v1/functionalities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(functionality))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/api/v1/functionalities/teste"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("teste"))
                .andExpect(jsonPath("$.description").value("teste"))
                .andExpect(jsonPath("$.canView").value(true))
                .andExpect(jsonPath("$.canView").value(true))
                .andExpect(jsonPath("$.canEdit").value(false));
    }

    @Test
    @DisplayName("Must return status code 200 when making GET request to endpoint - /api/v1/functionalities/{id}")
    void must_Return_A_Functionality_When_Given_An_Id() throws Exception {
        final FunctionalityResponse func = new FunctionalityResponse(1L, "teste", "teste", true, false);

        when(service.findFunctionalityById(any(Long.class))).thenReturn(ResponseEntity.ok().body(func));
        mockMvc.perform(get("/api/v1/functionalities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("teste"))
                .andExpect(jsonPath("$.description").value("teste"))
                .andExpect(jsonPath("$.canView").value(true))
                .andExpect(jsonPath("$.canView").value(true))
                .andExpect(jsonPath("$.canEdit").value(false));
    }

    @Test
    @DisplayName("Must return functionality list and status code 200 when making GET request to endpoint - /api/v1/functionalities")
    void must_Return_Functionality_List_All() throws Exception {
        final Set<FunctionalityResponse> listHashSet = new HashSet<>();
        listHashSet.add(new FunctionalityResponse(1L, "teste", "teste", true, false));
        listHashSet.add(new FunctionalityResponse(2L, "teste", "teste", true, false));
        listHashSet.add(new FunctionalityResponse(3L, "teste", "teste", true, false));
        listHashSet.add(new FunctionalityResponse(4L, "teste", "teste", true, false));

        when(service.listAllFunctionality()).thenReturn((ResponseEntity.ok().body(listHashSet)));

        mockMvc.perform(get("/api/v1/functionalities").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.size()").value(4));
    }

}