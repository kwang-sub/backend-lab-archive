package com.example.ch05;

import com.example.ch05.model.Item;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class HelloWoldJPATest {

    private static final Logger log = LoggerFactory.getLogger(HelloWoldJPATest.class);
    @Autowired
    private EntityManager em;

    @Test
    void 기본키_테스트() {
        Item item = Item.builder()
                .name("Some Item")
                .auctionEnd(Helper.tomorrow())
                .build();
        em.persist(item);
        log.debug("item={}", item.getId());
        assertThat(item.getId()).isNotNull();
    }
}
