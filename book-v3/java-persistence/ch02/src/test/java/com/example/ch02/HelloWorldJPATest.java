package com.example.ch02;

import com.example.ch02.entity.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloWorldJPATest {

    @Autowired
    private EntityManagerFactory emf;

    @Test
    void storeLoadMessage() {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Message message = new Message();
        message.setText("Hello World!");
        em.persist(message);

        em.getTransaction().commit();

        em.getTransaction().begin();

        List<Message> messages = em.createQuery("select m from Message m", Message.class).getResultList();

        messages.get(messages.size() - 1).setText("Hello Wold from JPA!");

        em.getTransaction().commit();
        assertThat(messages.size()).isEqualTo(1);
        assertThat(messages.get(0).getText()).isEqualTo("Hello Wold from JPA!");
        em.close();
    }
}
