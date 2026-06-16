package com.example.chap04.model;

import com.example.chap04.model.start.Board;
import com.example.chap04.model.start.RoleType;
import com.example.chap04.model.start.TableSequences;
import com.example.chap04.model.start.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            userTest(em);
//            boardTest(em);
//            tableSequencesTest(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void tableSequencesTest(EntityManager em) {
        TableSequences tableSequences = new TableSequences();
        em.persist(tableSequences);
    }

    private static void boardTest(EntityManager em) {
        Board board1 = new Board();
        Board board2 = new Board();
        em.persist(board1);
        em.persist(board2);
        System.out.println("board1  = " + board1.getId());
        System.out.println("board2  = " + board2.getId());

    }

    private static void userTest(EntityManager em) {
        User user1 = new User();
        user1.setUsername("kwang");
        user1.setAge(10);
        user1.setDescription("테스트 회원입니다");
        user1.setCreateDate(LocalDateTime.now());
        user1.setRoleType(RoleType.USER);
//        영속성 컨텍스트에 관리되어야 자동 할당됨!
        System.out.println("id값 확인" + user1.getId());
        em.persist(user1);
        System.out.println("id값 확인" + user1.getId());

        User user2 = new User();
        user2.setUsername("kwang2");
        user2.setAge(10);
        user2.setDescription("테스트 회원입니다");
        user2.setRoleType(RoleType.USER);

        em.persist(user2);
    }

}
