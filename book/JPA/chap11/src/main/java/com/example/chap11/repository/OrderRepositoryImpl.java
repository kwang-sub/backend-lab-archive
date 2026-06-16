package com.example.chap11.repository;

import com.example.chap11.domain.Order;
import com.example.chap11.dto.OrderSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.chap11.domain.QMember.*;
import static com.example.chap11.domain.QOrder.*;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> findAll(OrderSearch orderSearch) {

        return
                from(order)
                .leftJoin(member).fetchJoin()
                .where(orderSearchBuilder(orderSearch))
                .orderBy(order.id.desc())
                .fetch();
    }

    private static BooleanBuilder orderSearchBuilder(OrderSearch orderSearch) {
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            builder.and(member.name.eq(orderSearch.getMemberName()));
        }
        if (orderSearch.getOrderStatus() != null) {
            builder.and(order.status.eq(orderSearch.getOrderStatus()));
        }
        return builder;
    }
}
