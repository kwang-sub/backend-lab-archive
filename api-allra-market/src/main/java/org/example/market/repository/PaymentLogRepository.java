package org.example.market.repository;

import org.example.market.entity.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {

    List<PaymentLog> findByOrderId(Long orderId);
}
