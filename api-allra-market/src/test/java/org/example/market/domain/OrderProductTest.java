package org.example.market.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.example.market.client.PaymentClient;
import org.example.market.dto.request.OrderReqDto;
import org.example.market.dto.response.OrderResDto;
import org.example.market.entity.Cart;
import org.example.market.entity.Customer;
import org.example.market.entity.OrderProduct;
import org.example.market.entity.Product;
import org.example.market.factory.ObjectFactory;
import org.example.market.repository.OrderProductRepository;
import org.example.market.repository.PaymentLogRepository;
import org.example.market.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.example.market.enums.TransactionStatus.SUCCESS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Import(ObjectFactory.class)
public class OrderProductTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ObjectFactory objectFactory;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @MockitoBean
    private PaymentClient paymentClient;
    @Autowired
    private PaymentLogRepository paymentLogRepository;

    @BeforeEach
    public void setUp() {
        paymentLogRepository.deleteAll();
    }

    @Test
    void 주문시_주문상품이_생성된다() throws Exception {
        // given
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(SUCCESS));
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.TEN, 10);
        Cart cart = objectFactory.createCart(customer, product, 1);

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(List.of(cart));
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().isOk())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        OrderResDto response = objectMapper.readValue(responseString, OrderResDto.class);
        Long orderId = response.id();
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderId);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(orderProducts).hasSize(1);
        OrderProduct orderProduct = orderProducts.get(0);
        softAssertions.assertThat(orderProduct.getProduct().getId()).isEqualTo(product.getId());
        softAssertions.assertThat(orderProduct.getProduct().getPrice()).isEqualTo(product.getPrice());
        softAssertions.assertThat(orderProduct.getProduct().getName()).isEqualTo(product.getName());
        softAssertions.assertThat(orderProduct.getProduct().getDescription()).isEqualTo(product.getDescription());

        softAssertions.assertAll();

        verify(paymentClient, times(1)).requestPayment(any());
    }
}
