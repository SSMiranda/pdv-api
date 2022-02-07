package com.mirandasidney.pdv.api.controller;

import com.mirandasidney.pdv.api.domain.Module;
import com.mirandasidney.pdv.api.repository.ModuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ModuleController.class)
public class ModuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModuleRepository repository;

    @Test
    public void checkModuleSavedWithoutFunctionality() throws Exception {
        Module request = new Module();
        request.setChecked(true);
        request.setName("Dashboard");
        request.setDescription("Módulo de dashboard do sistema");

        Module response = new Module(1L, true, "Dashboard", "Módulo de dashboard do sistema",null);

        Mockito.when(repository.save(request)).thenReturn(response);

        this.mockMvc.perform(post("/api/v1/modules").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
