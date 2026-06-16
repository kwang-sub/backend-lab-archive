package org.example.ssiach07ex3.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
class TestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void testProduct() throws Exception {
        mvc.perform(get("/product/1234").with(httpBasic("john", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("1234"));
    }

    @Test
    void testProduct2() throws Exception {
        mvc.perform(get("/product/asdf").with(httpBasic("john", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("asdf"));
    }
}