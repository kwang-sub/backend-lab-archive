package com.example.chap11.service;

import com.example.chap11.domain.Member;
import com.example.chap11.domain.Order;
import com.example.chap11.domain.OrderStatus;
import com.example.chap11.domain.item.Book;
import com.example.chap11.domain.item.Item;
import com.example.chap11.exception.NotEnoughStockException;
import com.example.chap11.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void 주문저장_테스트() {
        Member member = createMember();
        Item book = createBook();
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order findOrder = orderRepository.findById(orderId).get();
        assertThat(findOrder).isNotNull();
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getTotalPrice()).isEqualTo(book.getPrice() * orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(8);
    }

    @Test
    void 상품주문_재고수량초과테스트() {
        Member member = createMember();
        Item book = createBook();
        int orderCount = 11;

        assertThatThrownBy(() -> orderService.order(member.getId(), book.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class);
    }

    @Test
    void 주문취소_테스트() {
        Member member = createMember();
        Item book = createBook();
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order findOrder = orderRepository.findById(orderId).get();

        orderService.cancelOrder(findOrder.getId());
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(book.getStockQuantity()).isEqualTo(10);
    }

    private Item createBook() {
        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(1000);
        book.setStockQuantity(10);
        itemService.saveItem(book);
        return book;
    }

    private Member createMember() {
        Member member = Member.builder()
                .name("회원1")
                .build();
        memberService.join(member);
        return member;
    }

}