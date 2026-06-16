package com.example.chap07.start.distinction.sample.embeddedid;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            logic(em);
            find(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void find(EntityManager em) {
        ParentIdEm parentIdEm = new ParentIdEm("Id1", "Id2");
        ParentEm findParentEm = em.find(ParentEm.class, parentIdEm);
        log.info("찾은 객체 : {}", findParentEm);
    }

    private static void logic(EntityManager em) {
        ParentEm parentEm = new ParentEm();
        ParentIdEm parentIdEm = new ParentIdEm("Id1", "Id2");
        parentEm.setId(parentIdEm);
        parentEm.setName("테스트");
        em.persist(parentEm);
    }
}
