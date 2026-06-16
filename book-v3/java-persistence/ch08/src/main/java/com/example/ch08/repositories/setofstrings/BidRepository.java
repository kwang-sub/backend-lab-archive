package com.example.ch08.repositories.setofstrings;

import com.example.ch08.entity.Bid;
import com.example.ch08.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid, Long> {

    Set<Bid> findByItem(Item item);
}
