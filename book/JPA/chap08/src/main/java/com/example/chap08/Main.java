package com.example.chap08;

import com.example.chap08.start.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;

import javax.persistence.*;
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
//            save(em);
//            printUserAndTeam(em);
//            printUser(em);
//            lazyTest(em);
//            orderTest(em);
//            saveNoCascade(em);
            saveWithCascade(em);
            removeTest(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }

    private static void removeTest(EntityManager em) {
        Parent parent = em.find(Parent.class, 1L);
        parent.getChildren().remove(0);
    }

    private static void saveWithCascade(EntityManager em) {
        Child child = new Child();
        Parent parent = new Parent();
        child.setParent(parent);
        em.persist(parent);
    }

    private static void saveNoCascade(EntityManager em) {
        Parent parent = new Parent();
        em.persist(parent);

        Child child1 = new Child();
        child1.setParent(parent);
        em.persist(child1);

        Child child2 = new Child();
        child2.setParent(parent);
        em.persist(child2);

        log.info("parent.get {}", parent.getChildren());
    }

    private static void orderTest(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        List<Order> orders = member.getOrders();
        log.info("orders = {}", orders.getClass().getName());
    }

    private static void lazyTest(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        Team team = member.getTeam();
        log.info("지연로딩 확인 {}", team.getClass());
    }

    private static void printUser(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        log.info("회원만 찾기 : {}", member.getUsername());

        Member proxy = em.getReference(Member.class, 2L);
        log.info("프록시 객체 {}", proxy.getClass());
        log.info("프록시 객체 초기화 여부 {}", em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(proxy));
    }

    private static void printUserAndTeam(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        Team team = member.getTeam();
        log.info("회원이름 : {}", member.getUsername());
        log.info("팀이름 : {}", team.getName());
    }

    private static void save(EntityManager em) {
        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Member member = new Member();
        member.setUsername("kwang");
        member.setTeam(team);
        em.persist(member);
    }
}
