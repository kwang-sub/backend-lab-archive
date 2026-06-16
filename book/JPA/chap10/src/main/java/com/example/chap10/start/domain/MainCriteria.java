package com.example.chap10.start.domain;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by 1001218 on 15. 4. 5..
 */
@Slf4j
public class MainCriteria {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            save(em);
//            start(em);
//            search(em);
//            search2(em);
            tuple(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

    }

    private static void tuple(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Member> m = cq.from(Member.class);
        cq.multiselect(
                m.get("name").alias("username"),
                m.get("age").alias("age")
        );
        List<Tuple> resultList = em.createQuery(cq).getResultList();
        for (Tuple tuple : resultList) {
            log.info("튜플 username {}", tuple.get("username", String.class));
            log.info("age {}", tuple.get(1));
        }
    }

    private static void search2(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);
        Root<Member> m = cq.from(Member.class);
        Predicate age = cb.greaterThan(m.<Integer>get("age"), 9);
        cq.select(m)
                .where(age)
                .orderBy(cb.desc(m.get("id")));
        List<Member> resultList = em.createQuery(cq).getResultList();
        for (Member member : resultList) {
            log.info("조건 조회 {}", member);
        }
    }

    private static void search(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);

        Predicate nameEqual = cb.equal(m.get("name"), "kwang");
        Predicate ageEqual = cb.equal(m.get("age"), 11);

        cq.select(m)
                .where(nameEqual, ageEqual);

        Member result = em.createQuery(cq).getSingleResult();
        log.info("검색 조건 추가 {}", result);
    }

    private static void start(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);
        cq.select(m);
        List<Member> resultList = em.createQuery(cq).getResultList();
        for (Member member : resultList) {
            log.info("조회된 멤버 {}", member);
        }
    }

    private static void save(EntityManager em) {
        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Member member = null;
        for (int i = 0; i < 10; i++) {
            member = new Member();
            member.setName("kwang");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);
        }
        member = new Member();
        member.setName("kwang");
        member.setAge(11);
        em.persist(member);

        Product product = new Product();
        product.setName("상품1");
        product.setPrice(1000);
        product.setStockAmount(100);
        em.persist(product);

        Order order = new Order();
        order.setMember(member);
        order.setOrderAmount(10);
        order.setProduct(product);
        order.setAddress(new Address("서울시", "**동", "100"));
        em.persist(order);
    }
}
