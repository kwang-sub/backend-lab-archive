package com.example.ch08.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Bid bid)) return false;
        return Objects.equals(id, bid.id) && Objects.equals(amount, bid.amount) && Objects.equals(item, bid.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, item);
    }
}
