package org.example.market.service;

import lombok.RequiredArgsConstructor;
import org.example.market.dto.request.OrderCartReqDto;
import org.example.market.entity.Order;
import org.example.market.entity.OrderProduct;
import org.example.market.entity.Product;
import org.example.market.repository.OrderProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;

    public void creatAll(final Order order, final Set<OrderCartReqDto> orderCarts) {
        List<OrderProduct> orderProducts = orderCarts.stream()
                .map(it -> {
                            Product product = productService.findEntityById(it.productId());
                            return OrderProduct.create(order, product, it.price(), it.quantity());
                        }
                ).toList();

        orderProductRepository.saveAll(orderProducts);
    }
}
