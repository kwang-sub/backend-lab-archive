package org.example.market.repository;

import org.example.market.entity.Cart;
import org.example.market.entity.Customer;
import org.example.market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomerAndProduct(Customer customer, Product product);

    Optional<Cart> findByIdAndCustomerId(Long id, Long customerId);

    List<Cart> findAllByCustomerId(Long customerId);
}
