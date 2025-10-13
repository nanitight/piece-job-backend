package com.nani.backend.piece_job_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.service.JwtService;
import com.nani.backend.piece_job_backend.service.PJUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = PJUserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PJUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PJUserService service;

    @MockitoBean
    private JwtService jwtService;


    @Autowired
    private ObjectMapper objectMapper;

    private PJUser user;

    @BeforeEach
    public void init(){
        user = PJUser.builder()
                .username("test1")
                .password("12345")
                .role("jobseeker")
                .employerType("individual")
                .build();
    }

    @Test
    public void PJUserController_CreateUser_ReturnsUser() throws Exception{
        given(service.register(ArgumentMatchers.any())).willAnswer(inv -> inv.getArgument(0)) ;

        ResultActions response = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))) ;

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
}
