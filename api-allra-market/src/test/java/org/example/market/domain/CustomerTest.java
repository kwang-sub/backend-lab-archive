package org.example.market.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.example.market.common.ApplicationConstant;
import org.example.market.dto.request.AuthReqDto;
import org.example.market.dto.request.CustomerReqDto;
import org.example.market.dto.response.AuthTokenResDto;
import org.example.market.dto.response.CustomerResDto;
import org.example.market.dto.response.FieldErrorResDto;
import org.example.market.entity.Customer;
import org.example.market.factory.ObjectFactory;
import org.example.market.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Import(ObjectFactory.class)
public class CustomerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ObjectFactory objectFactory;
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void 사용자를_등록할_수_있다() throws Exception {
        // given
        CustomerReqDto requestParameter = new CustomerReqDto("user", "1234");

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                ).andExpect(status().isOk())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        CustomerResDto response = objectMapper.readValue(responseString, CustomerResDto.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.id()).isNotNull();
        softAssertions.assertThat(response.name()).isEqualTo(requestParameter.name());

        softAssertions.assertAll();
    }

    @Test
    void 사용자명은_중복되면_안된다() throws Exception {
        Customer testUser = objectFactory.createCustomer("testUser", "password");
        CustomerReqDto requestParameter = new CustomerReqDto(testUser.getName(), "1234");
        // given
        MvcResult mvcResult = mvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                ).andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail()).isEqualTo("이미 등록된 사용자 이름입니다.");

        softAssertions.assertAll();
    }

    @Test
    void 사용자_등록시_이름_패스워드는_필수이다() throws Exception {
        // given
        CustomerReqDto requestParameter = new CustomerReqDto("", "");
        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                ).andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        @SuppressWarnings("unchecked")
        List<FieldErrorResDto> fieldErrors = (List<FieldErrorResDto>) Objects.requireNonNull(response.getProperties())
                .get(ApplicationConstant.Exception.FIELD_ERROR_KEY);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(fieldErrors).isNotNull();
        softAssertions.assertThat(fieldErrors)
                .extracting("field")
                .containsExactlyInAnyOrder(
                        "name",
                        "password"
                );


        softAssertions.assertAll();
    }

    @Test
    void 사용자_로그인시_인증_토큰이_발급된다() throws Exception {
        // given
        Customer testUser = objectFactory.createCustomer("testUser", "password");
        AuthReqDto authReqDto = new AuthReqDto(testUser.getName(), "password");

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/login")
                        .content(objectMapper.writeValueAsBytes(authReqDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        AuthTokenResDto response = objectMapper.readValue(responseString, AuthTokenResDto.class);
        String accessToken = response.accessToken();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(accessToken).isNotNull();
        softAssertions.assertThat(jwtUtil.extractSubject(accessToken)).isEqualTo(authReqDto.username());
        softAssertions.assertThat(jwtUtil.isValidateToken(accessToken, authReqDto.username())).isTrue();
        softAssertions.assertAll();
    }

    @Test
    void 사용자_로그인시_등록되지_않은_사용자는_인증이_안된다() throws Exception {
        // given
        AuthReqDto authReqDto = new AuthReqDto("anonymous", "password");

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/login")
                        .content(objectMapper.writeValueAsBytes(authReqDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail()).isEqualTo("올바르지 않은 인증 정보입니다.");
        softAssertions.assertAll();
    }
}
