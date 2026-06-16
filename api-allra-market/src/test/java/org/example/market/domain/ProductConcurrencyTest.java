package org.example.market.domain;

import org.example.market.entity.Product;
import org.example.market.factory.ObjectFactory;
import org.example.market.repository.ProductRepository;
import org.example.market.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ObjectFactory.class)
public class ProductConcurrencyTest {
    @Autowired
    private ObjectFactory objectFactory;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    void 재고감소는_동시성_처리가_된다() throws Exception {
        // given
        Product product = objectFactory.createProduct("상품1", null, BigDecimal.valueOf(1000), 100);

        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    productService.decreaseStock(product.getId(), 1);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        // then
        Product finalProduct = productService.findEntityById(product.getId());
        assertThat(finalProduct.getStock()).isEqualTo(0);

        productRepository.deleteAll();
        productRepository.flush();
    }

    @Test
    void 재고증가는_동시성_처리가_된다() throws Exception {
        // given
        Product product = objectFactory.createProduct("상품1", null, BigDecimal.valueOf(1000), 0);

        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    productService.increaseStock(product.getId(), 1);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        // then
        Product finalProduct = productService.findEntityById(product.getId());
        assertThat(finalProduct.getStock()).isEqualTo(100);

        productRepository.deleteAll();
        productRepository.flush();
    }

}
