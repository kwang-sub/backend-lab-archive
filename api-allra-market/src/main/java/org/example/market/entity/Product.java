package org.example.market.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.market.exception.StockShortageException;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "product")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "stock", nullable = false)
    private Integer stock;

    public static Product create(
            final String name,
            final String description,
            final BigDecimal price,
            final Integer stock
    ) {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build();
    }

    public void decreaseStock(Integer quantity) {
        if (this.stock - quantity < 0)
            throw new StockShortageException();

        this.stock -= quantity;
    }

    public void resetStock(Integer quantity) {
        this.stock += quantity;
    }
}