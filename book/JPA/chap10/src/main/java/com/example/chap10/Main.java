package com.example.chap10;

import com.example.chap10.start.domain.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            memberSave(em);
            jpqlTest(em);
            criteriaTest(em);
            sqlTest(em);
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }

    private static void sqlTest(EntityManager em) {
        String sql = "SELECT * FROM MEMBER WHERE NAME = 'kwang'";
        List resultList = em.createNativeQuery(sql, Member.class).getResultList();
        log.info("native : {}", resultList.get(0));
    }


    private static void criteriaTest(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);
        Root<Member> m = query.from(Member.class);
        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "kwang"));
        List<Member> resultList = em.createQuery(cq).getResultList();
        log.info("criteriaResult = {}", resultList.get(0));
    }

    private static void jpqlTest(EntityManager em) {
        String jpql = "select m from Member as m where m.name = 'kwang'";
        List<Member> resultList = em.createQuery(jpql, Member.class)
                .getResultList();

        log.info("member = {}", resultList.get(0));
    }

    private static void memberSave(EntityManager em) {
        Member member = new Member();
        member.setName("kwang");
        em.persist(member);
    }

}
