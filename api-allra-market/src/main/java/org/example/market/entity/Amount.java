package org.example.market.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Amount {
    @NotNull
    @Column(name = "total_amount", nullable = false)
    private BigDecimal total;

    @NotNull
    @Column(name = "payment_amount", nullable = false)
    private BigDecimal payment;

    public static Amount create(BigDecimal totalAmount) {
        return Amount.builder()
                .total(totalAmount)
                .payment(totalAmount)
                .build();
    }
}
