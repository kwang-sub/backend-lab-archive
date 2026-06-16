package org.example.ch12.proxy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemTest {

    @Autowired
    private EntityManagerFactory emf;

    private Long itemId;

    @BeforeAll
    void setUpBeforeClass() throws Exception {
        Item item = new Item();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
        itemId = item.getId();
    }


    @Test
    void test() {
        EntityManager em = emf.createEntityManager();
        Item item = em.getReference(Item.class, itemId);
        assertThat(item).isNotNull();
    }
}