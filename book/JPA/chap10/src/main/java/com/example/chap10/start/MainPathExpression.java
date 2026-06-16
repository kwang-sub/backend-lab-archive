package com.example.chap10.start;

import com.example.chap10.start.domain.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1001218 on 15. 4. 5..
 */
@Slf4j
public class MainPathExpression {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            save(em);
//            sample(em);
//            entityTest(em);
            named(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

    }

    private static void named(EntityManager em) {
        List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("name", "kwang")
                .getResultList();
        for (Member member : resultList) {
            log.info("네임드 쿼리 {}", member);
        }
    }

    private static void entityTest(EntityManager em) {
        Member member = new Member();
        member.setId(2L);

        String sql = "select m from Member m where m = :member";
        List findMember = em.createQuery(sql)
                .setParameter("member", member)
                .getResultList();
        log.info("엔티티로 찾은 회원 {}",findMember);

        Team team = em.find(Team.class, 1L);
        String sql2 = "select m from Member m where m.team = :team";
        List<Member> team1 = em.createQuery(sql2, Member.class)
                .setParameter("team", team)
                .setFirstResult(3)
                .setMaxResults(5)
                .getResultList();

        for (Member member1 : team1) {
            log.info("조회된 회원 {}", member1);
            log.info("동일성 테스트 {}", team == member1.getTeam());
        }
    }

    private static void sample(EntityManager em) {
        String jpql = "select m.name, m.age from Member m";
        List<Object[]> resultList = em.createQuery(jpql).getResultList();
        for (Object[] objects : resultList) {
            log.info("name = {} age = {}", objects[0], objects[1]);
        }

        String jpql2 = "select o.member.name, o.member.age from Order o";
        List<Object[]> resultList2 = em.createQuery(jpql2).getResultList();
        for (Object[] objects : resultList2) {
            log.info("오더로 조회 name = {} age = {}", objects[0], objects[1]);
        }

        String count = "select count(m) from Team t join t.members m where t.name = '팀1'";
        Object singleResult = em.createQuery(count).getSingleResult();
        log.info("멤버수 {}", singleResult);

        String avg = "select m from Member m where m.age > (select avg (m2.age) from Member m2)";
        Member singleResult1 = em.createQuery(avg, Member.class).getSingleResult();
        log.info("평균 보다 나이 많은 회원 {}", singleResult1);
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
