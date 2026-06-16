package org.example.ch11.concurrency;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ItemTest {

    @Autowired
    private EntityManagerFactory emf;

    private Long ITEM_ID;

    @Autowired
    Versioning versioning;

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        Item item = new Item();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
        ITEM_ID = item.getId();
    }

}