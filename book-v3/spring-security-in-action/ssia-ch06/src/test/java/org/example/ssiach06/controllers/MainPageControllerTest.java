package org.example.ssiach06.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.example.ssiach06.config.TestDataFactory;
import org.example.ssiach06.entities.Product;
import org.example.ssiach06.entities.Users;
import org.example.ssiach06.repositories.ProductRepository;
import org.example.ssiach06.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import(TestDataFactory.class)
class MainPageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestDataFactory testDataFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Users user;
    private Product product;

    @BeforeEach
    void setUp() {
        user = testDataFactory.creatUser();
        userRepository.save(user);

        product = testDataFactory.createProducts();
        productRepository.save(product);
    }

    @Test
    @DisplayName("인증 성공 응답 코드 확인")
    void test() throws Exception {
        String expectedJson = objectMapper.writeValueAsString(List.of(product));
        List<Product> all = productRepository.findAll();

        mvc.perform(get("/main").with(httpBasic("kwang", "123456")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(content().json(expectedJson));
    }
    
    @Test
    @DisplayName("인증 실패 응답 코드 확인")
    void fail() throws Exception {
        mvc.perform(get("/main")).andExpect(status().isUnauthorized());
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        productRepository.deleteAll();
    }
}