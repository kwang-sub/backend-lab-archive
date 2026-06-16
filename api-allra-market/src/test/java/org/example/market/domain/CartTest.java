package org.example.market.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.example.market.dto.request.CartAddReqDto;
import org.example.market.dto.request.CartModifyReqDto;
import org.example.market.entity.Cart;
import org.example.market.entity.Customer;
import org.example.market.entity.Product;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Import(ObjectFactory.class)
public class CartTest {

    @Autowired
    private ObjectFactory objectFactory;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 사용자는_장바구니에_상품을_담을수_있다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.valueOf(1000), 10);
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();
        CartAddReqDto requestParameter = new CartAddReqDto(product.getId(), 10);

        // when
        mvc.perform(post("/api/v1/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                        .header("Authorization", "Bearer " + token)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].product.id").value(requestParameter.productId()))
                .andExpect(jsonPath("$[0].quantity").value(requestParameter.quantity()));
    }

    @Test
    void 사용자는_장바구니에_중복된_상품을_담으면_개수가_합산된다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.valueOf(1000), 10);
        Cart cart = objectFactory.createCart(customer, product, 5);
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();
        CartAddReqDto requestParameter = new CartAddReqDto(product.getId(), 5);

        // when
        mvc.perform(post("/api/v1/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                        .header("Authorization", "Bearer " + token)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].product.id").value(requestParameter.productId()))
                .andExpect(jsonPath("$[0].quantity").value(10));
    }

    @Test
    void 사용자는_등록되지_않은_상품을_담을수_없다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();
        CartAddReqDto requestParameter = new CartAddReqDto(0L, 1);

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail())
                .isEqualTo("엔티티 정보를 찾을 수 없습니다. entity: [Product] identifier [0]");

        softAssertions.assertAll();
    }

    @Test
    void 사용자는_장바구니_내역을_수정할_수_있다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.valueOf(1000), 10);
        Cart cart = objectFactory.createCart(customer, product, 5);
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();
        CartModifyReqDto requestParameter = new CartModifyReqDto(1);

        // when
        mvc.perform(patch("/api/v1/carts/" + cart.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                        .header("Authorization", "Bearer " + token)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(cart.getId()))
                .andExpect(jsonPath("$[0].product.id").value(cart.getProduct().getId()))
                .andExpect(jsonPath("$[0].quantity").value(1));
    }


    @Test
    void 사용자는_등록되지_않은_장바구니_내역을_수정할_수_없다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();
        CartModifyReqDto requestParameter = new CartModifyReqDto(1);

        // when
        MvcResult mvcResult = mvc.perform(patch("/api/v1/carts/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail())
                .isEqualTo("엔티티 정보를 찾을 수 없습니다. entity: [Cart] identifier [0]");

        softAssertions.assertAll();
    }

    @Test
    void 사용자는_다른_사용자의_장바구니를_수정할_수_없다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.valueOf(1000), 10);
        Cart cart = objectFactory.createCart(customer, product, 5);

        Customer otherCustomer = objectFactory.createCustomer("otherUser", "password");
        String token = jwtUtil.generateAccessToken(otherCustomer.getName()).accessToken();
        CartModifyReqDto requestParameter = new CartModifyReqDto(1);

        // when
        MvcResult mvcResult = mvc.perform(patch("/api/v1/carts/" + cart.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail())
                .isEqualTo("엔티티 정보를 찾을 수 없습니다. entity: [Cart] identifier [" + cart.getId() + "]");

        softAssertions.assertAll();
    }


    @Test
    void 사용자는_장바구니_내역을_삭제할_수_있다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.valueOf(1000), 10);
        Cart cart = objectFactory.createCart(customer, product, 5);
        Product otherProduct = objectFactory.createProduct("상품2", null, BigDecimal.valueOf(1000), 10);
        Cart otherCart = objectFactory.createCart(customer, otherProduct, 5);

        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();

        // when
        mvc.perform(delete("/api/v1/carts/" + cart.getId())
                        .header("Authorization", "Bearer " + token)
                )

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void 사용자는_등록되지_않은_장바구니_내역을_삭제할_수_없다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();

        // when
        MvcResult mvcResult = mvc.perform(delete("/api/v1/carts/0")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail())
                .isEqualTo("엔티티 정보를 찾을 수 없습니다. entity: [Cart] identifier [0]");

        softAssertions.assertAll();
    }

    @Test
    void 사용자는_다른_사용자의_장바구니_내역을_삭제할_수_없다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.valueOf(1000), 10);
        Cart cart = objectFactory.createCart(customer, product, 5);

        Customer otherCustomer = objectFactory.createCustomer("otherUser", "password");
        String token = jwtUtil.generateAccessToken(otherCustomer.getName()).accessToken();

        // when
        MvcResult mvcResult = mvc.perform(delete("/api/v1/carts/" + cart.getId())
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail())
                .isEqualTo("엔티티 정보를 찾을 수 없습니다. entity: [Cart] identifier [" + cart.getId() + "]");

        softAssertions.assertAll();
    }

}
