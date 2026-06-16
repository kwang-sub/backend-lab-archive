package org.example.market.repository;

import org.example.market.entity.Order;
import org.example.market.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByCustomerIdAndOrderStatus(
            Long customerId,
            OrderStatus orderStatus,
            Pageable pageable
    );
}

