package com.example.chap11.service;

import com.example.chap11.domain.item.Item;
import com.example.chap11.domain.item.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {


    @Autowired
    private ItemService itemService;

    @Test
    void 아이템저장_테스트() {
        Movie movie = new Movie();
        movie.setName("영화1");
        movie.setActor("배우");
        movie.setDirector("감독");
        movie.setPrice(1000);
        Long saveId = itemService.saveItem(movie);

        Item findItem = itemService.findOne(saveId);

        assertThat(findItem).isNotNull();
        assertThat(findItem).isEqualTo(movie);
    }
}