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
import java.math.BigInteger;
import java.util.List;

import static com.example.chap10.start.domain.QMember.member;
import static com.example.chap10.start.domain.QOrder.order;
import static com.example.chap10.start.domain.QProduct.product;

/**
 * Created by 1001218 on 15. 4. 5..
 */
@Slf4j
public class MainNativeSQL {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            save(em);
//            basic(em);
//            mapping(em);
            multiTest(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }

    private static void multiTest(EntityManager em) {

        Member findMember = em.find(Member.class, 7L);
        log.info("회원 수정전 : {}", findMember.getName());

        String sql = "update Member m " +
                "set m.name = '수정 테스트' " +
                "where m.id > :id";
        int result = em.createQuery(sql)
                .setParameter("id", 5L)
                .executeUpdate();
        log.info("수정된 회원 수 : {}", result);
        em.refresh(findMember);
        log.info("회원 수정후 : {}", findMember.getName());
    }

    private static void mapping(EntityManager em) {
        String sql = "SELECT M.MEMBER_ID, M.AGE, M.NAME, M.TEAM_ID, I.ORDER_COUNT " +
                "FROM MEMBER M " +
                "LEFT JOIN " +
                "(SELECT IM.MEMBER_ID, COUNT(*) AS ORDER_COUNT " +
                "FROM ORDERS O, MEMBER IM " +
                "WHERE O.MEMBER_ID = IM.MEMBER_ID) I " +
                "ON M.MEMBER_ID = I.MEMBER_ID";
        Query nativeQuery = em.createNativeQuery(sql, "memberWithOrderCount");
        List<Object[]> resultList = nativeQuery.getResultList();
        for (Object[] row : resultList) {
            Member member = (Member) row[0];
            BigInteger orderCount = (BigInteger) row[1];

            log.info("멤버 = {}", member);
            log.info("오더수 = {}", orderCount);
        }
    }

    private static void basic(EntityManager em) {
        String sql = "SELECT MEMBER_ID, NAME, AGE, TEMA_ID " +
                "FROM MEMBER WHERE AGE > ?";

        List<Member> resultList = em.createNativeQuery(sql, Member.class)
                .setParameter(1, 10)
                .getResultList();

        for (Member member : resultList) {
            log.info("기본 조회 : {}", member);
            log.info("기본 조회 : {}", member.getTeam());
        }

        String sql2 = "SELECT MEMBER_ID, NAME, AGE, TEMA_ID FROM MEMBER WHERE AGE > ?";

        List<Object[]> resultList1 = em.createNativeQuery(sql2)
                .setParameter(1, 10)
                .getResultList();

        for (Object[] objects : resultList1) {
            log.info("objects : {}", objects[0]);
            log.info("objects : {}", objects[1]);
            log.info("objects : {}", objects[2]);
            log.info("objects : {}", objects[3]);
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
        member.setTeam(team);
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
