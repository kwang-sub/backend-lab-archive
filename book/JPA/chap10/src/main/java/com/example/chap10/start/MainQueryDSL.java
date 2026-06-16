package com.example.chap10.start;

import com.example.chap10.start.domain.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

import static com.example.chap10.start.domain.QMember.member;
import static com.example.chap10.start.domain.QOrder.*;
import static com.example.chap10.start.domain.QProduct.product;

/**
 * Created by 1001218 on 15. 4. 5..
 */
@Slf4j
public class MainQueryDSL {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            save(em);
//            queryDSL(em);
//            basic(em);
//            search(em);
//            paging(em);
//            paging2(em);
//            groupByTest(em);
//            join(em);
//            subQuery(em);
//            tupleTest(em);
//            beanTest(em);
//            update(em);
            booleanBuilderTest(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }

    private static void booleanBuilderTest(EntityManager em) {
        SearchParam searchParam = new SearchParam();
        searchParam.setName("kwang");
        searchParam.setAge(11);

        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(searchParam.getName())) {
            builder.and(member.name.contains(searchParam.getName()));
        }
        if (searchParam.getAge() != null) {
            builder.and(member.age.eq(searchParam.getAge()));
        }

        JPAQuery<Member> query = new JPAQuery<>(em);
        Member member1 = query.from(member)
                .where(builder)
                .fetchOne();
        log.info("빌더 이용 조회 : {}", member1);
    }

    private static void update(EntityManager em) {
        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, member);
        long result = jpaUpdateClause.where(member.id.eq(2L))
                .set(member.name, "수정")
                .execute();
        log.info("수정 결과 {}", result);
    }

    private static void beanTest(EntityManager em) {
        JPAQuery<UserDTO> userDTOJPAQuery = new JPAQuery<>(em);
        List<UserDTO> fetch = userDTOJPAQuery.from(member)
                .select(Projections.constructor(UserDTO.class,
                        member.name,
                        member.age))
                .fetch();
        for (UserDTO userDTO : fetch) {
            log.info("DTO 변환 : {}", userDTO);
        }
    }

    private static void tupleTest(EntityManager em) {
        JPAQuery<Object> query = new JPAQuery<>(em);
        List<Tuple> list = query.from(member)
                .select(member.age, member.name)
                .fetch();
        for (Tuple tuple : list) {
            log.info("튜플 age {}", tuple.get(member.age));
            log.info("name {}", tuple.get(member.name));
        }
    }

    private static void subQuery(EntityManager em) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        List<Member> resultList = query.from(member)
                .where(member.age.eq(
                        JPAExpressions
                                .select(member.age.max())
                                .from(member)
                ))
                .fetch();

        for (Member member1 : resultList) {
            log.info("서브쿼리 {}", member1);
        }
    }

    private static void join(EntityManager em) {
        JPAQuery<Order> query = new JPAQuery<>(em);
        List<Order> fetch = query.from(order)
                .join(order.member, member)
                .leftJoin(order.product, product)
                .fetch();

        for (Order order : fetch) {
            log.info("조인 조회 : {}", order);
        }

        JPAQuery<Order> query2 = new JPAQuery<>(em);
        List<Order> orderList = query2.from(order)
                .leftJoin(order.product, product)
                .on(product.name.contains("상품"))
                .fetch();
        for (Order order1 : orderList) {
            log.info("on 조회 : {}", order1);
        }

        JPAQuery<Order> query3 = new JPAQuery<>(em);
        List<Order> fetch1 = query3.from(order)
                .innerJoin(order.member, member).fetchJoin()
                .leftJoin(order.product, product).fetchJoin()
                .fetch();
        for (Order order1 : fetch1) {
            log.info("페치 조인 적용 {}",order1);
        }

        JPAQuery<Object> objectJPAQuery = new JPAQuery<>(em);
        List<Object> fetch2 = objectJPAQuery.from(order, member)
//                .where(order.member.eq(member))
                .distinct()
                .fetch();
        for (Object o : fetch2) {
            log.info("프롬절 중복 : {}", o);
        }
    }

    private static void groupByTest(EntityManager em) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        List<Tuple> fetch = query.from(member)
                .select(member.age.sum(), member.team.name)
                .groupBy(member.team)
                .fetch();

        for (Tuple tuple : fetch) {
            log.info("조회한 {}",tuple.get(0, String.class));
            log.info("조회한 {}",tuple.get(1, String.class));
        }
    }

    private static void paging2(EntityManager em) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        List<Member> fetch = query.from(member)
                .offset(0).limit(4)
                .fetch();
        log.info("총 수 : {}", fetch.size());
        for (Member member : fetch) {
            log.info("검색된 회원 : {}", member);
        }
    }

    private static void paging(EntityManager em) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        List<Member> resultList = query.from(member)
                .orderBy(member.id.desc())
                .offset(0).limit(5)
                .createQuery()
                .getResultList();

        for (Member member1 : resultList) {
            log.info("페이징 : {}", member1);
        }
    }

    private static void search(EntityManager em) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        Object singleResult = query.from(member)
                .where(member.name.contains("n").and(member.age.gt(10)))
                .createQuery()
                .getSingleResult();
        log.info("검색 조회 {}", singleResult);
    }

    private static void basic(EntityManager em) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        List<Member> query1 = query.from(member)
                .where(member.age.gt(10))
                .fetch();
        for (Member member1 : query1) {
            log.info("테스트 : {}", member1);
        }
    }

    private static void queryDSL(EntityManager em) {
        JPAQuery<Member> query = new JPAQuery<>(em);
        QMember m = new QMember("m");
        List<Member> query1 = query.from(member)
                .where(member.age.gt(10))
                .orderBy(member.id.desc())
                .createQuery()
                .getResultList();
        for (Member member : query1) {
            log.info("조회된 회원 {}", member);
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

        Product product2 = new Product();
        product2.setName("물건1");
        product2.setPrice(1000);
        product2.setStockAmount(100);
        em.persist(product2);

        Order order = new Order();
        order.setMember(member);
        order.setOrderAmount(10);
        order.setProduct(product2);
        order.setAddress(new Address("서울시", "**동", "100"));
        em.persist(order);
    }
}
