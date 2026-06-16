package com.example.ch08.onetomany;

import com.example.ch08.entity.Bid;
import com.example.ch08.entity.Item;
import com.example.ch08.repositories.setofstrings.BidRepository;
import com.example.ch08.repositories.setofstrings.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MappingAssociationsSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Test
    void test() throws InterruptedException {
        Item item = Item.builder()
                .name("Foo")
                .build();

        Bid bid1 = Bid.builder()
                .item(item)
                .amount(BigDecimal.valueOf(100))
                .build();

        Bid bid2 = Bid.builder()
                .item(item)
                .amount(BigDecimal.valueOf(200))
                .build();
        item.addBid(bid1);
        item.addBid(bid2);

        Set<Bid> bids = item.getBids();
        System.out.println(bids.size() + " sdf");
        itemRepository.save(item);

        assertThat(item.getId()).isNotNull();
        assertThat(bid1.getId()).isNotNull();
        assertThat(bid2.getId()).isNotNull();

        Item findItem = itemRepository.findItemWithBids(item.getId());
        Bid firstBid = findItem.getBids().iterator().next();
        findItem.removeBid(firstBid);
        itemRepository.save(item);
        itemRepository.flush();

    }
}
