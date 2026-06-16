package com.example.chap10.start;

import com.example.chap10.start.domain.*;
import lombok.extern.slf4j.Slf4j;

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
            save(em);
//            typedQueryTest(em);
//            queryTest(em);
//            singleTest(em);
//            paramTest(em);
//            projectionTest(em);
//            multiProjectionTest(em);
//            dtoTest(em);
//            pagingTest(em);
//            groupTest(em);
        joinTest(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
        EntityManager em2 = emf.createEntityManager();
        em2.close();
    }

    private static void joinTest(EntityManager em) {
        String inner = "SELECT m FROM Member m inner JOIN m.team t";
        List<Member> resultList = em.createQuery(inner, Member.class).getResultList();
        for (Member member : resultList) {
            log.info("조인 조회 결과 11살 회원 나오면 안됨 {}", member);
        }

        String outer = "SELECT m FROM Member m LEFT JOIN m.team t";
        List<Member> outerList = (List<Member>) em.createQuery(outer, Member.class).getResultList();
        for (Member member : outerList) {
            log.info("아우터 조인 11살 회원 나와야됨 : {}", member);
        }

        String teamJoin = "SELECT m FROM Team t join t.members m";
        List<Member> resultList1 = em.createQuery(teamJoin, Member.class).getResultList();
        for (Member member : resultList1) {
            log.info("팀으로 조인 조회한 회원 {}", member);
        }

        String whereJoin = "select m from Member m, Team t " +
                "where m.team.name = t.name";
        List<Member> resultList2 = em.createQuery(whereJoin, Member.class).getResultList();
        for (Member member : resultList2) {
            log.info("세타 조인 : {}", member);
        }

        String fetchJoin = "select m from Member m join fetch m.team";
        List<Member> resultList3 = em.createQuery(fetchJoin, Member.class).getResultList();
        for (Member member : resultList3) {
            log.info("페치조인 {}", member);
        }

        String fetchJoin2 = "select distinct t from Team t join fetch t.members m";
        List<Team> resultList4 = em.createQuery(fetchJoin2, Team.class).getResultList();
        for (Team team : resultList4) {
            log.info("team 페치조인 : {}", team.getMembers());
        }
    }

    private static void groupTest(EntityManager em) {
        Object singleResult = em.createQuery("SELECT  sum(m.age) FROM Member m").getSingleResult();
        log.info("나이 합계 {}", singleResult);
    }

    private static void pagingTest(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m order by m.id desc ", Member.class);
        query.setFirstResult(3);
        query.setMaxResults(4);
        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            log.info("페이징 테스트 {}", member);
        }
    }

    private static void dtoTest(EntityManager em) {
        List<Object[]> resultList = em.createQuery("SELECT m.name, m.age FROM Member m")
                .getResultList();
        UserDTO userDTO = null;
        for (Object[] objects : resultList) {
            userDTO = new UserDTO((String) objects[0], (Integer) objects[1]);
        }
        log.info("UserDTO : {}", userDTO);

        List<UserDTO> resultList1 = em.createQuery("SELECT new com.example.chap10.start.UserDTO(m.name, m.age) FROM Member m", UserDTO.class)
                .getResultList();
        for (UserDTO dto : resultList1) {
            log.info("New UserDTO : {}", userDTO);
        }


    }

    private static void multiProjectionTest(EntityManager em) {
        String jpql = "SELECT o.member, o.product, o.orderAmount FROM Order o";
        List<Object[]> resultList = em.createQuery(jpql).getResultList();
        for (Object[] objects : resultList) {
            log.info("member : {}", objects[0]);
            log.info("product : {}", objects[1]);
            log.info("orderAmount : {}", objects[2]);
        }
    }

    private static void projectionTest(EntityManager em) {
        String jpql = "SELECT m.name, m.age FROM Member m";
        List<Object[]> resultList = em.createQuery(jpql).getResultList();
        for (Object[] result : resultList) {
            log.info("object[0] = {}", result[0]);
            log.info("object[1] = {}", result[1]);
        }
    }

    private static void paramTest(EntityManager em) {
        String sql = "SELECT m FROM Member m WHERE m.name = :username";
        Member result = em.createQuery(sql, Member.class)
                .setParameter("username", "kwang")
                .getSingleResult();
        log.info("paramTest1 : {}", result);

        String sql2 = "SELECT m FROM Member m WHERE m.name = ?1";
        Member result2 = em.createQuery(sql2, Member.class)
                .setParameter(1, "kwang")
                .getSingleResult();
        log.info("paramTest2 : {}", result2);
    }

    private static void singleTest(EntityManager em) {
        String sql = "SELECT m FROM Member m WHERE m.name = 'kwang'";
        TypedQuery<Member> query = em.createQuery(sql, Member.class);
        Member result = query.getSingleResult();
        log.info("singleTest : {}", result);
    }

    private static void queryTest(EntityManager em) {
        String jpql = "SELECT m FROM Member m";
        Query query = em.createQuery(jpql);
        List resultList = query.getResultList();
        for (Object o : resultList) {
            log.info("query조회 = {}", o);
        }
    }

    private static void typedQueryTest(EntityManager em) {
        String jpql = "SELECT m FROM Member m";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            log.info("조회 회원 : {}", member);
        };
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
