package org.example.ssiach2ex2.controller

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testHello() {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
            .with(SecurityMockMvcRequestPostProcessors.httpBasic("john", "12345")))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").value("Hello World!"))
    }

    @Test
    fun testFailHello() {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(status().is4xxClientError)
    }
}