package com.example.chap05.entity;

import com.example.chap05.start.domain.Team;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 1001218 on 15. 4. 5..
 */
@Slf4j
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            test(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void test(EntityManager em) {
        Member member = Member.builder()
                .name("user1")
                .city("서울")
                .build();

        em.persist(member);

        Item item = Item.builder()
                .name("아이템1")
                .price(1000)
                .stockQuantity(100)
                .build();

        em.persist(item);

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDER)
                .build();

        order.setMember(member);

        OrderItem orderItem = new OrderItem();
        orderItem.setCount(10);
        orderItem.setItem(item);
        em.persist(orderItem);

        order.addOrderItem(orderItem);
        em.persist(order);
    }

}
