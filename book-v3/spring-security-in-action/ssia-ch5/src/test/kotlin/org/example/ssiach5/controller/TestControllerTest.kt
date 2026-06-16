package org.example.ssiach5.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun helloTest() {
        mvc.perform(get("/hello").with(httpBasic("kwang", "1234")))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value("hello kwang!"))
            .andExpect(header().exists("Content-Length"))
            .andExpect(header().doesNotExist("message"))

    }

    @Test
    fun byeTest() {
        val result = mvc.perform(get("/bye").with(httpBasic("kwang", "1234")))
            .andExpect(status().isOk)
            .andReturn()

        val content = result.response.contentAsString
        assertEquals("bye kwang!", content)
    }

    @Test
    fun helloFailTest() {
        mvc.perform(get("/hello"))
            .andExpect(status().isUnauthorized)
            .andExpect(header().string("message", "Luke, I am your father!"))
    }
}