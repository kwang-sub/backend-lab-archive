package org.example.ssiach16ex1.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testHello() throws Exception {
        mvc.perform(get("/hello").with(httpBasic("emma", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Hello Fantastico"));
    }

    @Test
    void testHello2() throws Exception {
        mvc.perform(get("/hello").with(httpBasic("natalie", "12345")))
                .andExpect(status().isForbidden());
    }

    @Test
    void testSeHello() throws Exception {
        mvc.perform(get("/secret/names/emma").with(httpBasic("emma", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Fantastico"));
    }

    @Test
    void testSeHello2() throws Exception {
        mvc.perform(get("/secret/names/natalie").with(httpBasic("natalie", "12345")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0]").value("Energico"));
    }
}