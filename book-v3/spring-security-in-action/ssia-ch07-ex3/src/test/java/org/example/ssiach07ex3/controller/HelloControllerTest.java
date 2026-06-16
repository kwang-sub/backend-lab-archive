package org.example.ssiach07ex3.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testHello() throws Exception {
        mvc.perform(get("/hello").with(httpBasic("john", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Hello!"));
    }

    @Test
    void testHello2() throws Exception {
        mvc.perform(get("/hello").with(httpBasic("jane", "12345")))
                .andExpect(status().isForbidden());
    }

    @Test
    void testCiao() throws Exception {
        mvc.perform(get("/ciao").with(httpBasic("jane", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Ciao!"));
    }

    @Test
    void testCiao2() throws Exception {
        mvc.perform(get("/ciao").with(httpBasic("john", "12345")))
                .andExpect(status().isForbidden());
    }
}