package org.example.market.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.example.market.client.PaymentClient;
import org.example.market.dto.request.OrderReqDto;
import org.example.market.dto.response.OrderResDto;
import org.example.market.entity.*;
import org.example.market.enums.OrderStatus;
import org.example.market.enums.PaymentType;
import org.example.market.factory.ObjectFactory;
import org.example.market.repository.CartRepository;
import org.example.market.repository.OrderRepository;
import org.example.market.repository.PaymentLogRepository;
import org.example.market.repository.ProductRepository;
import org.example.market.service.CartService;
import org.example.market.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.market.enums.TransactionStatus.FAILED;
import static org.example.market.enums.TransactionStatus.SUCCESS;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Import(ObjectFactory.class)
public class PaymentTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ObjectFactory objectFactory;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PaymentLogRepository paymentLogRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @MockitoBean
    private CartService cartService;

    @MockitoBean
    private PaymentClient paymentClient;

    @BeforeEach
    public void setUp() {
        paymentLogRepository.deleteAll();
    }

    @Test
    void 주문시_결제가_성공하면_정상_내역이_쌓여야한다() throws Exception {
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
        List<PaymentLog> paymentLogs = paymentLogRepository.findByOrderId(response.id());

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(paymentLogs).hasSize(1);
        PaymentLog paymentLog = paymentLogs.get(0);
        softAssertions.assertThat(paymentLog.getStatus()).isEqualTo(SUCCESS);
        softAssertions.assertThat(paymentLog.getTransactionId()).isNotNull();
        softAssertions.assertThat(paymentLog.getFailureReason()).isNull();

        softAssertions.assertAll();

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문시_결제가_성공하면_주문상태는_결제완료_상태가_된다() throws Exception {
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

        assertThat(response.orderStatus()).isEqualTo(OrderStatus.PAYMENT_COMPLETE);

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문시_결제가_실패하면_응답상태_5xx을_반환하며_실패_내역이_쌓여야한다() throws Exception {
        // given
        List<PaymentLog> before = paymentLogRepository.findAll();
        assertThat(before).hasSize(0);
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(FAILED));
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
                .andExpect(status().is5xxServerError())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getDetail()).isEqualTo("Payment failed while creating the order. Status: PAYMENT_FAILED");
        List<PaymentLog> after = paymentLogRepository.findAll();
        softAssertions.assertThat(after).hasSize(1);
        PaymentLog paymentLog = after.get(0);
        softAssertions.assertThat(paymentLog.getStatus()).isEqualTo(FAILED);
        softAssertions.assertThat(paymentLog.getTransactionId()).isNull();
        softAssertions.assertThat(paymentLog.getFailureReason()).isNotEmpty();

        softAssertions.assertAll();

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문시_결제가_실패하면_주문상태는_결제실패_상태가_된다() throws Exception {
        // given
        List<Order> before = orderRepository.findAll();
        assertThat(before).hasSize(0);
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(FAILED));
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.TEN, 10);
        Cart cart = objectFactory.createCart(customer, product, 1);

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(List.of(cart));
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();


        // when
        mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().is5xxServerError());

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        List<Order> after = orderRepository.findAll();
        softAssertions.assertThat(after).hasSize(1);
        Order order = after.get(0);
        softAssertions.assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PAYMENT_FAILED);

        softAssertions.assertAll();

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문시_결제가_실패하면_상품재고는_차감되면_안된다() throws Exception {
        // given
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(FAILED));
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.TEN, 10);
        Cart cart = objectFactory.createCart(customer, product, 1);

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(List.of(cart));
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();


        // when
        mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().is5xxServerError());

        // then
        Product resultProduct = productRepository.findById(product.getId())
                .orElse(null);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultProduct).isNotNull();
        softAssertions.assertThat(resultProduct.getStock()).isEqualTo(10);

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문시_결제가_실패하면_장바구니는_초기화되면_안된다() throws Exception {
        // given
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(FAILED));
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.TEN, 10);
        Cart cart = objectFactory.createCart(customer, product, 1);

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(List.of(cart));
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();


        // when
        mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().is5xxServerError());

        // then
        Cart resultCart = cartRepository.findById(cart.getId())
                .orElse(null);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultCart).isNotNull();
        softAssertions.assertThat(resultCart.getProduct()).isEqualTo(product);

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문시_결제성공후_예외발생하면_결제취소_요청과_이력데이터가_쌓여야한다() throws Exception {
        // given
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(SUCCESS));
        when(paymentClient.requestPaymentCancel(any())).thenReturn(objectFactory.createPaymentResDto(SUCCESS));
        doThrow(RuntimeException.class).when(cartService).clear(any());
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.TEN, 10);
        Cart cart = objectFactory.createCart(customer, product, 1);

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(List.of(cart));
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();

        // when
        mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().is5xxServerError());

        // then
        List<PaymentLog> paymentLogs = paymentLogRepository.findAll();
        List<PaymentLog> requestPaymentLogs = paymentLogs.stream().filter(it -> it.getType().equals(PaymentType.PAYMENT))
                .toList();
        List<PaymentLog> requestPaymentCancelLogs = paymentLogs.stream().filter(it -> it.getType().equals(PaymentType.PAYMENT_CANCEL))
                .toList();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(paymentLogs).hasSize(2);
        softAssertions.assertThat(requestPaymentLogs).hasSize(1);
        softAssertions.assertThat(requestPaymentCancelLogs).hasSize(1);

        softAssertions.assertAll();

        verify(paymentClient, times(1)).requestPayment(any());
        verify(paymentClient, times(1)).requestPaymentCancel(any());
    }
}
