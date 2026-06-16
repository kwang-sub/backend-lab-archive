package org.example.market.service;

import lombok.RequiredArgsConstructor;
import org.example.market.dto.response.PaymentResDto;
import org.example.market.entity.PaymentLog;
import org.example.market.enums.PaymentType;
import org.example.market.repository.PaymentLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentLogRepository paymentLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create(final Long orderId, final PaymentResDto dto, final PaymentType type) {
        PaymentLog paymentLog = PaymentLog.create(orderId, dto, type);
        paymentLogRepository.save(paymentLog);
    }
}
