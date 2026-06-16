package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public ResponseOrder createOrder(RequestOrder orderDetail) {
        orderDetail.setOrderId(UUID.randomUUID().toString());
        orderDetail.setTotalPrice(orderDetail.getUnitPrice() * orderDetail.getQty());
        Order order = orderMapper.toEntity(orderDetail);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Transactional(readOnly = true)
    public ResponseOrder getOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId).map(orderMapper::toDto).orElseThrow(() -> new EntityNotFoundException(orderId));
    }

    @Transactional(readOnly = true)
    public List<ResponseOrder> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId).stream().map(orderMapper::toDto).toList();
    }
}
