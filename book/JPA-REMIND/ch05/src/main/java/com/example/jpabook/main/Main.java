package com.example.jpabook.main;

import com.example.jpabook.entity.Member;
import com.example.jpabook.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        testSave(em);
        updateRelation(em);
        deleteRelation(em);
//        testFind(em);
//        queryLogicJoin(em);

        tx.commit();
        em.close();
        emf.close();
    }

    private static void deleteRelation(EntityManager em) {
        Member member2 = em.getReference(Member.class, "member2");
        member2.setTeam(null);
    }

    private static void updateRelation(EntityManager em) {
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);
        Member member = em.getReference(Member.class, "member1");
        member.setTeam(team2);
    }

    private static void queryLogicJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where t.name =: teamName";
        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();
        resultList.forEach(member -> System.out.println(member.getUsername()));
    }

    private static void testFind(EntityManager em) {
        Member findMember = em.find(Member.class, "member1");
        Team findMemberTeam = findMember.getTeam();
        System.out.println("찾은 회원 : " + findMember.getUsername() + " 팀은?" + findMemberTeam.getName());
    }

    private static void testSave(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }
}
