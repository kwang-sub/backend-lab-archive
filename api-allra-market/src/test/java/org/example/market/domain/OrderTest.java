package org.example.market.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.example.market.client.PaymentClient;
import org.example.market.dto.request.OrderCartReqDto;
import org.example.market.dto.request.OrderReqDto;
import org.example.market.dto.response.OrderResDto;
import org.example.market.entity.Cart;
import org.example.market.entity.Customer;
import org.example.market.entity.Order;
import org.example.market.entity.Product;
import org.example.market.enums.OrderStatus;
import org.example.market.exception.InvalidOrderStatusException;
import org.example.market.factory.ObjectFactory;
import org.example.market.repository.CartRepository;
import org.example.market.repository.PaymentLogRepository;
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
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.market.enums.TransactionStatus.SUCCESS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Import(ObjectFactory.class)
public class OrderTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ObjectFactory objectFactory;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PaymentLogRepository paymentLogRepository;

    @MockitoBean
    private PaymentClient paymentClient;

    @BeforeEach
    public void setUp() {
        paymentLogRepository.deleteAll();
    }

    @Test
    void 사용자는_장바구니에_담긴_모든_상품들을_합산해서_주문할_수_있다() throws Exception {
        // given
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(SUCCESS));
        Customer customer = objectFactory.createCustomer("testUser", "password");
        List<Product> products = objectFactory.createProducts(4);
        List<Cart> carts = objectFactory.createCarts(customer, products, 1);

        BigDecimal totalAmount = carts.stream()
                .map(cart -> cart.getProduct().getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(carts);
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

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.id()).isNotNull();
        softAssertions.assertThat(response.amount().getTotal()).isEqualTo(totalAmount);
        softAssertions.assertThat(response.amount().getPayment()).isEqualTo(totalAmount);
        softAssertions.assertThat(response.orderStatus()).isEqualTo(OrderStatus.PAYMENT_COMPLETE);
        softAssertions.assertThat(response.orderAt()).isNotNull();

        softAssertions.assertAll();

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문의_최초_생성_상태는_결제대기_상태이다() {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");

        // when
        Order order = Order.create(customer, BigDecimal.ZERO);

        // then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.AWAITING_PAYMENT);
    }

    @Test
    void 사용자는_장바구니_일부_상품만_주문할_수_없다() throws Exception {
        // given
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(SUCCESS));
        Customer customer = objectFactory.createCustomer("testUser", "password");
        List<Product> products = objectFactory.createProducts(4);
        List<Cart> carts = objectFactory.createCarts(customer, products, 1);

        List<Cart> orderCart = List.of(carts.get(0));

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(orderCart);
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail()).isEqualTo("유효하지 않은 주문 내역입니다.");

        softAssertions.assertAll();
    }


    @Test
    void 사용자는_다른_사용자의_장바구니에_담긴_상품을_주문할_수_없다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품", null, BigDecimal.TEN, 10);
        Cart cart = objectFactory.createCart(customer, product, 1);

        Customer otherCustomer = objectFactory.createCustomer("otherUser", "password");
        OrderReqDto requestParameter = objectFactory.createOrderReqDto(List.of(cart));
        String token = jwtUtil.generateAccessToken(otherCustomer.getName()).accessToken();

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail()).isEqualTo("유효하지 않은 주문 내역입니다.");

        softAssertions.assertAll();
    }

    @Test
    void 사용자는_주문시_합산금액이_일치해도_요청한_개별_상품금액이_실제_상품금액과_상이하면_주문이_안된다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product productOne = objectFactory.createProduct("상품1", null, BigDecimal.valueOf(1000), 10);
        Product productTwo = objectFactory.createProduct("상품2", null, BigDecimal.valueOf(1000), 10);
        Cart cartOne = objectFactory.createCart(customer, productOne, 1);
        Cart cartTwo = objectFactory.createCart(customer, productTwo, 1);

        OrderReqDto requestParameter = new OrderReqDto(
                BigDecimal.valueOf(2000),
                Set.of(
                        new OrderCartReqDto(cartOne.getId(), cartOne.getProduct().getId(), cartOne.getQuantity(), BigDecimal.valueOf(500)),
                        new OrderCartReqDto(cartTwo.getId(), cartTwo.getProduct().getId(), cartTwo.getQuantity(), BigDecimal.valueOf(1500))
                )
        );

        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail()).isEqualTo("유효하지 않은 주문 내역입니다.");

        softAssertions.assertAll();
    }

    @Test
    void 주문시_상품_재고가_처리된다() throws Exception {
        // given
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(SUCCESS));
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
                .andExpect(status().isOk());

        // then
        assertThat(product.getStock()).isEqualTo(9);

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문은_상품의_재고가_부족하면_주문이_안된다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품1", null, BigDecimal.valueOf(1000), 10);
        Cart cart = objectFactory.createCart(customer, product, 11);

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(List.of(cart));

        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();

        // when
        MvcResult mvcResult = mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String responseString = mvcResult.getResponse().getContentAsString();
        ProblemDetail response = objectMapper.readValue(responseString, ProblemDetail.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).isNotNull();
        softAssertions.assertThat(response.getDetail()).isEqualTo("상품 재고가 부족합니다.");

        softAssertions.assertAll();
    }

    @Test
    void 주문완료시_장바구니는_초기화된다() throws Exception {
        // given
        when(paymentClient.requestPayment(any())).thenReturn(objectFactory.createPaymentResDto(SUCCESS));
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Product product = objectFactory.createProduct("상품1", null, BigDecimal.valueOf(1000), 10);
        Cart cart = objectFactory.createCart(customer, product, 10);

        OrderReqDto requestParameter = objectFactory.createOrderReqDto(List.of(cart));

        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();

        // when
        mvc.perform(post("/api/v1/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestParameter))
                )
                .andExpect(status().isOk())
                .andReturn();

        // then
        List<Cart> carts = cartRepository.findAllByCustomerId(customer.getId());
        assertThat(carts).isEmpty();

        verify(paymentClient, times(1)).requestPayment(any());
    }

    @Test
    void 주문상태변경은_가능한_상태외에_불가능하다() {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        Order order = objectFactory.createOrder(customer, BigDecimal.ZERO);
        order.changeOrderStatus(OrderStatus.PAYMENT_COMPLETE);
        // when
        assertThatThrownBy(() -> order.changeOrderStatus(OrderStatus.AWAITING_PAYMENT))
                // then
                .isInstanceOf(InvalidOrderStatusException.class);
    }

    @Test
    void 주문완료내역을_조회할_수_있다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        List<Order> orders = objectFactory.createCompleteOrders(customer, 30);
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();
        Order maxIdProduct = orders.stream()
                .max(Comparator.comparing(Order::getId))
                .orElse(null);
        // when
        mvc.perform(get("/api/v1/orders/completed")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id,desc")
                )
        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(10))
                .andExpect(jsonPath("$.content[0].id").value(maxIdProduct.getId()))
                .andExpect(jsonPath("$.totalElements").value(30));
    }

    @Test
    void 주문완료외에_다른_상태는_조회되지_않는다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        List<Order> orders = objectFactory.createCompleteOrders(customer, 30);
        Order awaitingOrder = objectFactory.createOrder(customer, BigDecimal.ZERO);
        String token = jwtUtil.generateAccessToken(customer.getName()).accessToken();
        Order maxIdProduct = orders.stream()
                .max(Comparator.comparing(Order::getId))
                .orElse(null);
        // when
        mvc.perform(get("/api/v1/orders/completed")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id,desc")
                )
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(30));
    }

    @Test
    void 본인주문외에_안료된_다른_주문은_조회되지_않는다() throws Exception {
        // given
        Customer customer = objectFactory.createCustomer("testUser", "password");
        List<Order> orders = objectFactory.createCompleteOrders(customer, 30);

        Customer otherCustomer = objectFactory.createCustomer("otherUser", "password");
        String token = jwtUtil.generateAccessToken(otherCustomer.getName()).accessToken();

        // when
        mvc.perform(get("/api/v1/orders/completed")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id,desc")
                )
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(0));
    }
}
