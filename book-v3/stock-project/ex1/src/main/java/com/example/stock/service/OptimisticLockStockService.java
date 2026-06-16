package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OptimisticLockStockService {

    private final StockRepository stockRepository;

    public void decrease(Long productId, Long quantity) {
        Stock stock = stockRepository.findByProductIdWithOptimisticLock(productId).orElseThrow();
        stock.decrease(quantity);

        stockRepository.save(stock);
    }
}
