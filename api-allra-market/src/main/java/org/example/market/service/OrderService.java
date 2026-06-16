package org.example.market.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.market.client.PaymentClient;
import org.example.market.dto.request.OrderReqDto;
import org.example.market.dto.request.PaymentReqDto;
import org.example.market.dto.response.OrderResDto;
import org.example.market.dto.response.PaymentResDto;
import org.example.market.entity.Customer;
import org.example.market.entity.Order;
import org.example.market.enums.OrderStatus;
import org.example.market.enums.PaymentType;
import org.example.market.exception.InvalidOrderException;
import org.example.market.mapper.OrderMapper;
import org.example.market.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;
    private final CustomerService customerService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderProductService orderProductService;
    private final PaymentService paymentLogService;

    public OrderResDto createOrderWithPayment(final Long customerId, final OrderReqDto dto) {
        validateOrder(customerId, dto);

        Customer customer = customerService.findEntityById(customerId);
        Order order = Order.create(customer, dto.totalAmount());
        orderRepository.save(order);

        productService.decreaseStock(dto.orderCarts());
        PaymentResDto paymentResponse = paymentClient.requestPayment(new PaymentReqDto(order.getId(), order.getAmount().getPayment()));

        switch (paymentResponse.status()) {
            case SUCCESS -> {
                try {
                    paymentSuccessProcessing(customerId, dto, order, paymentResponse);
                } catch (Exception e) {
                    paymentRollbackProcessing(paymentResponse);
                    throw e;
                }
            }
            case FAILED -> paymentFailedProcessing(dto, order, paymentResponse);
        }

        return orderMapper.toDto(order);
    }

    private void paymentSuccessProcessing(final Long customerId, final OrderReqDto dto, final Order order, final PaymentResDto paymentResponse) {
        orderProductService.creatAll(order, dto.orderCarts());
        cartService.clear(customerId);
        order.changeOrderStatus(OrderStatus.PAYMENT_COMPLETE);
        orderRepository.save(order);
        paymentLogService.create(order.getId(), paymentResponse, PaymentType.PAYMENT);
    }

    private void paymentRollbackProcessing(final PaymentResDto paymentResponse) {
        log.error("Order processing failed after payment completion. Initiating payment rollback. Transaction ID: {}",
                paymentResponse.transactionId());
        paymentLogService.create(null, paymentResponse, PaymentType.PAYMENT);
        PaymentResDto paymentCancelResponse = paymentClient.requestPaymentCancel(paymentResponse.transactionId());
        paymentLogService.create(null, paymentCancelResponse, PaymentType.PAYMENT_CANCEL);
    }

    private void paymentFailedProcessing(final OrderReqDto dto, final Order order, final PaymentResDto paymentResponse) {
        productService.increaseStock(dto.orderCarts());
        order.changeOrderStatus(OrderStatus.PAYMENT_FAILED);
        paymentLogService.create(order.getId(), paymentResponse, PaymentType.PAYMENT);
        orderRepository.save(order);
    }

    private void validateOrder(final Long customerId, final OrderReqDto dto) {
        this.validateTotalAmount(dto);
        cartService.validateOrderCarts(customerId, dto.orderCarts());
    }

    private void validateTotalAmount(final OrderReqDto dto) {
        BigDecimal totalAmount = dto.orderCarts().stream()
                .map(cart -> cart.price().multiply(BigDecimal.valueOf(cart.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        if (totalAmount.compareTo(dto.totalAmount().setScale(2, RoundingMode.HALF_UP)) != 0)
            throw new InvalidOrderException();
    }

    @Transactional(readOnly = true)
    public Page<OrderResDto> getCompletedOrders(final Long consumerId, final Pageable page) {
        return orderRepository.findAllByCustomerIdAndOrderStatus(consumerId, OrderStatus.PAYMENT_COMPLETE, page)
                .map(orderMapper::toDto);
    }
}
