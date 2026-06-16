package org.example.ssiach10ex01.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Get Hello"));

//        2mB4F0Nwz6ARmPlsCSjIDB26_QwJPjdU07Zf8ft9D2XxM4gQ61dKJCIUrsM8_ZpcMAX8OXuK0G0-CQ955IZsyZkfNlaQA-km
    }

    @Test
    void testPost() throws Exception {
        mvc.perform(post("/hello").header("X-CSRF-TOKEN", "2mB4F0Nwz6ARmPlsCSjIDB26_QwJPjdU07Zf8ft9D2XxM4gQ61dKJCIUrsM8_ZpcMAX8OXuK0G0-CQ955IZsyZkfNlaQA-km"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Post Hello"));
    }
}