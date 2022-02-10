package com.mirandasidney.pdv.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequest;
import com.mirandasidney.pdv.api.controller.dto.response.module.ModuleResponse;
import com.mirandasidney.pdv.api.service.interfaces.IModuleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ModuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IModuleService service;

    @Test
    @DisplayName("Must return status code 201 when making POST request to endpoint - /api/v1/modules")
    void mustSaveModuleWithoutFunctionalitySpecified() throws Exception {
        ModuleRequest module = new ModuleRequest(true, "Dashboard", "Dashboards module of system");
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/modules/{name}")
                .buildAndExpand(module.getName())
                .toUri();
        ModuleResponse response = new ModuleResponse(UUID.randomUUID(), true, "Dashboard", "Dashboards module of system");
        when(service.save(any(ModuleRequest.class))).thenReturn(ResponseEntity.created(uri).body(response));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/modules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(module))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/api/v1/modules/Dashboard"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Dashboard"))
                .andExpect(jsonPath("$.description").value("Dashboards module of system"))
                .andExpect(jsonPath("$.functionalities").isEmpty())
                .andExpect(jsonPath("$.checked").value(true));
    }

    @Test
    @DisplayName("Must return status code 404 when making GET request to endpoint - /api/v1/modules/{id} with incorrect id")
    void must_Return_Status_Code_404_When_Module_Id_Not_Exist() throws Exception {
        mockMvc.perform(get("/api/v1/modules/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Must return module list and status code 200 when making GET request to endpoint - /api/v1/modules")
    void must_Return_Module_List() throws Exception {
        Set<ModuleResponse> modulesHashSet = new HashSet<>();
        modulesHashSet.add(new ModuleResponse(UUID.randomUUID(),true,"Dashboards Modules of system","Dashboard"));
        modulesHashSet.add(new ModuleResponse(UUID.randomUUID(),true,"Users Modules of system","User"));
        modulesHashSet.add(new ModuleResponse(UUID.randomUUID(),true,"Reports Modules of system","Report"));

        when(service.listAllModules()).thenReturn(ok().body(modulesHashSet));
        mockMvc.perform(get("/api/v1/modules").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.size()").value(3));
    }
}
