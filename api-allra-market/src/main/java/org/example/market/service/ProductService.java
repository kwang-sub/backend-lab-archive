package org.example.market.service;

import lombok.RequiredArgsConstructor;
import org.example.market.common.ApplicationConstant;
import org.example.market.dto.request.OrderCartReqDto;
import org.example.market.dto.response.ProductResDto;
import org.example.market.entity.Product;
import org.example.market.exception.EntityNotFoundException;
import org.example.market.mapper.ProductMapper;
import org.example.market.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductResDto> getPage(final Pageable page) {
        return productRepository.findAllByStockGreaterThanEqual(ApplicationConstant.Product.MINIMUM_ORDER_QUANTITY, page)
                .map(productMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Product findEntityById(final Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, id));
    }

    public void decreaseStock(final Set<OrderCartReqDto> orderCarts) {
        orderCarts.forEach(cart ->
                this.decreaseStock(cart.productId(), cart.quantity())
        );
    }

    public void decreaseStock(final Long id, final Integer quantity) {
        Product product = productRepository.findByIdWithPessimisticLock(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, id));
        product.decreaseStock(quantity);
        productRepository.save(product);
    }

    public void increaseStock(final Set<OrderCartReqDto> orderCarts) {
        orderCarts.forEach(cart ->
                this.increaseStock(cart.productId(), cart.quantity())
        );
    }

    public void increaseStock(final Long id, final Integer quantity) {
        Product product = productRepository.findByIdWithPessimisticLock(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, id));
        product.resetStock(quantity);
        productRepository.save(product);
    }
}
