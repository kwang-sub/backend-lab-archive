package org.example.market.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.market.dto.response.PaymentResDto;
import org.example.market.enums.PaymentType;
import org.example.market.enums.TransactionStatus;

@Getter
@Entity
@Table(name = "payment_log")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentLog extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 25)
    private PaymentType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 25)
    private TransactionStatus status;

    @Size(max = 100)
    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @Size(max = 100)
    @Column(name = "failure_reason", length = 100)
    private String failureReason;

    @Column(name = "order_id")
    private Long orderId;

    public static PaymentLog create(final Long orderId, final PaymentResDto dto, final PaymentType type) {
        PaymentLog paymentLog = PaymentLog.builder()
                .type(type)
                .status(dto.status())
                .transactionId(dto.transactionId())
                .orderId(orderId)
                .build();

        if (dto.status().equals(TransactionStatus.FAILED))
            paymentLog.failureReason = dto.message();

        return paymentLog;
    }
}