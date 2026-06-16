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
//            testDetached(em);
//            clearTest(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
        mergeTest(emf);

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void mergeTest(EntityManagerFactory emf) {
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx = em1.getTransaction();
        tx.begin();
        Member member = em1.find(Member.class, "id1");
        em1.clear();
//        준영속에서 반영 시도 결과 적용안됨!
        member.setUsername("반영안됨");
        tx.commit();
        em1.close();

        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        tx2.begin();
        Member beforeMember = em2.find(Member.class, "id1");
        System.out.println("반영됨으로 나옴 :  " + beforeMember);

//        merge를 이용해 준영속 엔티티인 member 영속으로 만듬
        Member member2 = em2.merge(member);
        member2.setUsername("반영됨2");
        tx2.commit();
        em2.close();
    }

    private static void clearTest(EntityManager em) {
        Member member = em.find(Member.class, "id1");
        System.out.println(member);
        member.setUsername("수정됨");
        em.clear();

    }

    private static void testDetached(EntityManager em) {
        String id = "id2";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        //등록
        em.persist(member);
//        준영속이라 DB 반영 안됨
        em.detach(member);

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
