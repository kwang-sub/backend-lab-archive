package com.example.ch05.model;

import com.example.ch05.repository.BidRepository;
import com.example.ch05.repository.ItemBidSummaryRepository;
import com.example.ch05.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemBidSummaryTest {

    @Autowired
    private ItemBidSummaryRepository itemBidSummaryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @BeforeEach
    void tearDown() {
        Item item = Item.builder()
                .name("test")
                .build();
        itemRepository.save(item);

        Bid bid = Bid.builder()
                .item(item)
                .build();
        bidRepository.save(bid);
    }

    @Test
    void getItemBidSummary() {
        List<ItemBidSummary> summaries = itemBidSummaryRepository.findAll();

        assertThat(summaries).isNotEmpty();
        assertThat(summaries.size()).isEqualTo(1);
    }
}