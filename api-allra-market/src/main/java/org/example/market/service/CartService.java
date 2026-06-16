package org.example.market.service;

import lombok.RequiredArgsConstructor;
import org.example.market.dto.request.CartAddReqDto;
import org.example.market.dto.request.OrderCartReqDto;
import org.example.market.dto.response.CartResDto;
import org.example.market.entity.Cart;
import org.example.market.entity.Customer;
import org.example.market.entity.Product;
import org.example.market.exception.EntityNotFoundException;
import org.example.market.exception.InvalidOrderException;
import org.example.market.mapper.CartMapper;
import org.example.market.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public List<CartResDto> getCarts(final Long customerId) {
        return cartRepository.findAllByCustomerId(customerId)
                .stream()
                .map(cartMapper::toDto)
                .toList();
    }

    public List<CartResDto> addCart(final Long customerId, final CartAddReqDto dto) {
        Customer customer = customerService.findEntityById(customerId);
        Product product = productService.findEntityById(dto.productId());

        Cart cart = cartRepository.findByCustomerAndProduct(customer, product)
                .orElse(Cart.create(customer, product, 0));

        cart.addQuantity(dto.quantity());
        cartRepository.save(cart);

        return getCarts(customerId);
    }

    public List<CartResDto> updateCart(final Long id, final Long customerId, final Integer quantity) {
        Cart cart = cartRepository.findByIdAndCustomerId(id, customerId)
                .orElseThrow(() -> new EntityNotFoundException(Cart.class, id));
        cart.updateQuantity(quantity);
        cartRepository.save(cart);

        return getCarts(customerId);
    }

    public List<CartResDto> deleteCart(Long id, Long customerId) {
        Cart cart = cartRepository.findByIdAndCustomerId(id, customerId)
                .orElseThrow(() -> new EntityNotFoundException(Cart.class, id));
        cartRepository.delete(cart);

        return getCarts(customerId);
    }

    public void validateOrderCarts(final Long customerId, final Set<OrderCartReqDto> orderCarts) {
        List<Cart> customerCarts = cartRepository.findAllByCustomerId(customerId);

        if (customerCarts.size() != orderCarts.size())
            throw new InvalidOrderException();

        boolean isValid = customerCarts.stream()
                .allMatch(cart -> orderCarts.stream()
                        .anyMatch(orderCart ->
                                Objects.equals(cart.getId(), orderCart.cartId()) &&
                                        Objects.equals(cart.getProduct().getId(), orderCart.productId()) &&
                                        Objects.equals(cart.getQuantity(), orderCart.quantity()) &&
                                        Objects.equals(cart.getProduct().getPrice(), orderCart.price())
                        )
                );

        if (!isValid) throw new InvalidOrderException();
    }

    public void clear(final Long customerId) {
        List<Cart> carts = cartRepository.findAllByCustomerId(customerId);
        cartRepository.deleteAll(carts);
    }
}
