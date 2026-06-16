package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember("memberA", "회원1");
        member.setUsername("회원명변경");
        mergeMember(member);
    }

    private static void mergeMember(Member member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member mergeMember = em.merge(member);
        tx.commit();

        System.out.println("member = " + member.getUsername());
        System.out.println("mergeMember = " + mergeMember.getUsername());

        System.out.println("em contains member = " + em.contains(member));
        System.out.println("em contains mergeMember = " + em.contains(mergeMember));
        em.close();
    }

    private static Member createMember(String id, String username) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        
        em.persist(member);
        tx.commit();
        em.close(); // member 준영속 상태로 만들어버림
        return member;
    }
}
