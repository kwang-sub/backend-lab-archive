package org.example.ch11.versionall;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import org.example.ch11.concurrency.ConcurrencyItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VersionalItemTest {

    @Autowired
    private EntityManagerFactory emf;

    private Long ITEM_ID;

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        VersionalItem item = new VersionalItem();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
        ITEM_ID = item.getId();
    }

    @Test
    void test() {
        EntityManager em1 = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();

        em1.getTransaction().begin();
        VersionalItem emOneConcurrencyItem = em1.find(VersionalItem.class, ITEM_ID);
        assertThat(emOneConcurrencyItem).isNotNull();
        emOneConcurrencyItem.setName("New Item");

        em2.getTransaction().begin();
        VersionalItem emTwoConcurrencyItem = em2.find(VersionalItem.class, ITEM_ID);
        assertThat(emTwoConcurrencyItem).isNotNull();
        emTwoConcurrencyItem.setName("Updated Item");

        em1.flush();
        em1.getTransaction().commit();
        em1.close();

        assertThatThrownBy(() -> em2.flush())
                .isInstanceOf(OptimisticLockException.class);

        VersionalItem newEmTwoConcurrencyItem = em2.find(VersionalItem.class, ITEM_ID);
        assertThat(newEmTwoConcurrencyItem).isNotNull();
    }
}