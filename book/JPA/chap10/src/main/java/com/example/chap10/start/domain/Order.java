package com.example.chap10.start.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;
    private int orderAmount;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public void setMember(Member member) {
        member.getOrders().add(this);
        this.member = member;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderAmount=" + orderAmount +
                ", address=" + address +
                ", product=" + product +
                ", member=" + member.getId() +
                '}';
    }
}
