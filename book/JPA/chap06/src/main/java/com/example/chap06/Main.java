package com.example.chap06;

import com.example.chap06.start.mapping.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            save(em);
            find(em);
            saveOrder(em);
            findOrder(em);

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void findOrder(EntityManager em) {
        Order order = em.find(Order.class, 1L);
        log.info("findOrder : {}", order);
    }

    private static void saveOrder(EntityManager em) {
        Member member = new Member();
        member.setUsername("회원1");
        em.persist(member);

        Product product = new Product();
        product.setId("productB");
        product.setName("상품1");
        em.persist(product);

        Order order = new Order();
        order.setMember(member);
        order.setProduct(product);
        order.setOrderAmount(10);

        em.persist(order);
    }

    private static void find(EntityManager em) {
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember(1L);
        memberProductId.setProduct("productA");
        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);
        log.info("memberProduct : {}", memberProduct);
    }

    private static void save(EntityManager em) {
        Member member = new Member();
        member.setUsername("회원1");
        em.persist(member);

        Product product = new Product();
        product.setId("productA");
        product.setName("상품1");
        em.persist(product);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member);
        memberProduct.setProduct(product);
        memberProduct.setOrderAmount(10);

        em.persist(memberProduct);
    }


/*
    private static void find2(EntityManager em) {
        Product productA = em.find(Product.class, "productA");
        Member member = productA.getMembers().get(0);
        log.info("조회2 username : {}", member.getUsername());
    }

    private static void find(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        List<Product> products = member.getProducts();
        for (Product product : products) {
            log.info("상품조회 : {}", product.getMembers().get(0).getUsername() );
        }
    }

    private static void save(EntityManager em) {
        Product product = new Product("productA", "상품A");
        em.persist(product);

        Member member = new Member();
        member.setUsername("user1");
        member.addProduct(product);
        em.persist(member);

    }
*/
}
