package org.example.market.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.market.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false, length = 25)
    private OrderStatus orderStatus;

    @Embedded
    private Amount amount;

    @NotNull
    @Column(name = "order_at", nullable = false)
    private LocalDateTime orderAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public static Order create(final Customer customer, final BigDecimal totalAmount) {
        return Order.builder()
                .orderStatus(OrderStatus.AWAITING_PAYMENT)
                .amount(Amount.create(totalAmount))
                .orderAt(LocalDateTime.now())
                .customer(customer)
                .build();
    }

    public void changeOrderStatus(final OrderStatus orderStatus) {
        this.orderStatus.validateOrderStatus(orderStatus);
        this.orderStatus = orderStatus;
    }
}