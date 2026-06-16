package com.example.ch05.repository;

import com.example.ch05.model.ItemBidSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemBidSummaryRepository extends JpaRepository<ItemBidSummary, Long> {
}

