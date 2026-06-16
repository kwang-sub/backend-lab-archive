package com.example.chap11.service;

import com.example.chap11.datajpa.MemberRepository2;
import com.example.chap11.domain.Delivery;
import com.example.chap11.domain.Member;
import com.example.chap11.domain.Order;
import com.example.chap11.domain.OrderItem;
import com.example.chap11.domain.item.Item;
import com.example.chap11.dto.OrderSearch;
import com.example.chap11.repository.MemberRepository;
import com.example.chap11.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository2 memberRepository;
    private final ItemService itemService;

    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId).get();
        Item item = itemService.findOne(itemId);

        Delivery delivery = new Delivery(member.getAddress());
        OrderItem orderItem = OrderItem.createOrderItem(item, count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }

}
