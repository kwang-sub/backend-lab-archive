package com.example.chap04.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ORDER_ITEM")
@NoArgsConstructor
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column(name = "ITEM_ID")
    private Long itemId;

    @Column(name = "ORDER_ID")
    private Long orderId;

    private int orderPrice;
    private int count;

    @Builder
    public OrderItem(Long itemId, Long orderId, int orderPrice, int count) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
