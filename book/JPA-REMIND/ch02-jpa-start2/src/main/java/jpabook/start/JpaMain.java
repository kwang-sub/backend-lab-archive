package jpabook.start;

import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
//            logic(em);  //비즈니스 로직
//            logic1(em);
            logic2(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void logic2(EntityManager em) {
        Member member1 = new Member();
        member1.setId("member1");
        member1.setAge(20);
        em.persist(member1);

        member1.setAge(10);

        Member findMember = em.find(Member.class, "member1");
        System.out.println("수정후 회원 찾기 = " + findMember);
    }

    //    쿼리 한번에 날아감(쓰기지연)
    private static void logic1(EntityManager em) {
        Member member1 = new Member();
        member1.setId("member1");
        member1.setAge(20);
        em.persist(member1);
        Member findMember1 = em.find(Member.class, "member1");
        Member findMember2 = em.find(Member.class, "member3");
        System.out.println("회원 찾기 = " + findMember1);
        System.out.println("없는 회원 찾기 = " + findMember2);
        Member member2 = new Member();
        member2.setId("member2");
        member2.setAge(20);
        em.persist(member2);
    }

    public static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);
    }
}
