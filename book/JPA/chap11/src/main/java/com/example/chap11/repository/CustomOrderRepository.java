package com.example.chap11.repository;

import com.example.chap11.domain.Order;
import com.example.chap11.dto.OrderSearch;

import java.util.List;

public interface CustomOrderRepository {
    List<Order> findAll(OrderSearch orderSearch);
}
