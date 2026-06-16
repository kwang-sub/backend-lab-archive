package com.example.chap11.service;

import com.example.chap11.domain.Address;
import com.example.chap11.domain.Member;
import com.example.chap11.domain.Order;
import com.example.chap11.domain.item.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NPlusOneTest {

    @Autowired
    MemberService memberService;

    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;

    @BeforeEach
    void setup() {
        Book book = new Book();
        book.setName("책");
        book.setPrice(1000);
        book.setStockQuantity(10);
        itemService.saveItem(book);
        Member member = null;
        Order order = null;
        for (int i = 0; i < 5; i++) {

            member = new Member("kwang" + i, new Address("테스트", "", ""));
            memberService.join(member);

            order = new Order();
            order.setMember(member);
            orderService.order(member.getId(), book.getId(), 1);
        }
    }

    @Test
    void 테스트() {
        System.out.println("=========================조회 쿼리시작");
        memberService.findMembers();
    }


}
