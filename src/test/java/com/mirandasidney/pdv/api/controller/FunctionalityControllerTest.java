//package com.mirandasidney.pdv.api.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mirandasidney.pdv.api.controller.dto.request.functionality.FunctionalityRequest;
//import com.mirandasidney.pdv.api.controller.dto.request.module.ModuleRequestById;
//import com.mirandasidney.pdv.api.controller.dto.response.functionality.FunctionalityResponse;
//import com.mirandasidney.pdv.api.service.interfaces.IFunctionalityService;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.ResponseEntity.created;
//import static org.springframework.http.ResponseEntity.ok;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class FunctionalityControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private IFunctionalityService service;
//
//    @Test
//    @Disabled
//    @DisplayName("Must return status code 201 when making POST request to endpoint - /api/v1/functionalities")
//    void must_Return201_When_New_Functionality() throws Exception {
//        final FunctionalityRequest functionality = new FunctionalityRequest("teste", "teste", new ModuleRequestById(), true);
//        final FunctionalityResponse response = new FunctionalityResponse(UUID.randomUUID(), functionality.getName(), functionality.getDescription(), true);
//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/api/v1/functionalities/{name}")
//                .buildAndExpand(functionality.getName())
//                .toUri();
//        when(service.save(any(FunctionalityRequest.class))).thenReturn(created(uri).body(response));
//
//        mockMvc.perform(post("/api/v1/functionalities")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(functionality))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(header().string("Location", "http://localhost/api/v1/functionalities/teste"))
//                .andExpect(status().is(201))
//                .andExpect(jsonPath("$.uuid").value(response.getUuid()))
//                .andExpect(jsonPath("$.name").value("teste"))
//                .andExpect(jsonPath("$.description").value("teste"))
//                .andExpect(jsonPath("$.canView").value(true))
//                .andExpect(jsonPath("$.canView").value(true))
//                .andExpect(jsonPath("$.canEdit").value(false));
//    }
//
//    @Test
//    @Disabled
//    @DisplayName("Must return status code 200 when making GET request to endpoint - /api/v1/functionalities/{id}")
//    void must_Return_A_Functionality_When_Given_An_Id() throws Exception {
//        final FunctionalityResponse func = new FunctionalityResponse(UUID.randomUUID(), "teste", "teste", true);
//
//        when(service.findFunctionalityById(any(UUID.class))).thenReturn(ok().body(func));
//        mockMvc.perform(get("/api/v1/functionalities/"+ func.getUuid())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200))
////                .andExpect(jsonPath("$.uuid").value(func.getUuid()))
//                .andExpect(jsonPath("$.name").value("teste"))
//                .andExpect(jsonPath("$.description").value("teste"))
//                .andExpect(jsonPath("$.canView").value(true))
//                .andExpect(jsonPath("$.canView").value(true))
//                .andExpect(jsonPath("$.canEdit").value(false));
//    }
//
//    @Test
//    @Disabled
//    @DisplayName("Must return functionality list and status code 200 when making GET request to endpoint - /api/v1/functionalities")
//    void must_Return_Functionality_List_All() throws Exception {
//        final Set<FunctionalityResponse> listHashSet = new HashSet<>();
//        listHashSet.add(new FunctionalityResponse(UUID.randomUUID(), "teste", "teste", false));
//        listHashSet.add(new FunctionalityResponse(UUID.randomUUID(), "teste", "teste", false));
//        listHashSet.add(new FunctionalityResponse(UUID.randomUUID(), "teste", "teste", false));
//        listHashSet.add(new FunctionalityResponse(UUID.randomUUID(), "teste", "teste",false));
//
//        when(service.listAllFunctionality()).thenReturn((ok().body(listHashSet)));
//
//        mockMvc.perform(get("/api/v1/functionalities").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200))
//                .andExpect(jsonPath("$.size()").value(4));
//    }
//
//}
