package com.example.chap05.start;

import com.example.chap05.start.domain.Member;
import com.example.chap05.start.domain.Team;
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
//            testSave(em);
//            queryLogicJoin(em);
//            updateRelation(em);
//            deleteRelation(em);
            testSaveNonOwner(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
//        EntityManager em2 = emf.createEntityManager();
//        biDirection(em2);


        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void testSaveNonOwner(EntityManager em) {
        Member member1 = new Member();
        member1.setUsername("user1");
        em.persist(member1);
        Member member2 = new Member();
        member2.setUsername("user2");
        em.persist(member2);

        Team team = new Team();
        team.setName("팀1");
        em.persist(team);
        team.getMembers().add(member1);
        team.getMembers().add(member2);

        Member findMember1 = em.find(Member.class, 1L);
        log.info("팀 설정 DB 반영 안된 회원 = {}",findMember1.getTeam());
    }

    private static void biDirection(EntityManager em) {
        Team team = em.find(Team.class, 1L);
        log.info("팀 객체 {}",team.getName());
        List<Member> members = team.getMembers();
        for (Member member : members) {
            log.info("팀으로 조회한 객체 = {}", member.getUsername());
        }

    }

    private static void deleteRelation(EntityManager em) {
        Member member = em.find(Member.class, 1L);
        member.setTeam(null);
    }

    private static void updateRelation(EntityManager em) {
        Team team = new Team();
        team.setName("팀2");
        em.persist(team);

        Member member = em.find(Member.class, 1L);
        member.setTeam(team);


    }

    private static void queryLogicJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where t.name=:teamName";
        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();
        for (Member member : resultList) {
            log.info("[query] member.username {}", member.getUsername());
        }
    }

    private static void testSave(EntityManager em) {
        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        Member member = new Member();
        member.setUsername("user1");
        member.setTeam(team);
        em.persist(member);

        Member member2 = new Member();
        member2.setUsername("user2");
        member2.setTeam(team);
        em.persist(member2);

        log.info("member1 조회 : {}",em.find(Member.class, 1L) );

    }

}
