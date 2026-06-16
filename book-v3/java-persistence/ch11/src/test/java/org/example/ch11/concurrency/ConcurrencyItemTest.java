package org.example.ch11.concurrency;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ConcurrencyItemTest {

    @Autowired
    private EntityManagerFactory emf;

    private Long ITEM_ID;

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        ConcurrencyItem concurrencyItem = new ConcurrencyItem();
        em.getTransaction().begin();
        em.persist(concurrencyItem);
        em.getTransaction().commit();
        em.close();
        ITEM_ID = concurrencyItem.getId();
    }

    @Test
    void test() {
        EntityManager em1 = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();

        em1.getTransaction().begin();
        ConcurrencyItem emOneConcurrencyItem = em1.find(ConcurrencyItem.class, ITEM_ID);
        assertThat(emOneConcurrencyItem).isNotNull();
        assertThat(emOneConcurrencyItem.getVersion()).isEqualTo(0);
        emOneConcurrencyItem.setName("New Item");

        em2.getTransaction().begin();
        ConcurrencyItem emTwoConcurrencyItem = em2.find(ConcurrencyItem.class, ITEM_ID);
        assertThat(emTwoConcurrencyItem).isNotNull();
        assertThat(emTwoConcurrencyItem.getVersion()).isEqualTo(0);
        emTwoConcurrencyItem.setName("Updated Item");

        em1.flush();
        assertThat(emOneConcurrencyItem.getVersion()).isEqualTo(1);
        em1.getTransaction().commit();
        em1.close();

        assertThatThrownBy(() -> em2.flush())
                .isInstanceOf(OptimisticLockException.class);

        ConcurrencyItem newEmTwoConcurrencyItem = em2.find(ConcurrencyItem.class, ITEM_ID);
        assertThat(newEmTwoConcurrencyItem).isNotNull();
        assertThat(newEmTwoConcurrencyItem.getVersion()).isEqualTo(0);
    }
}