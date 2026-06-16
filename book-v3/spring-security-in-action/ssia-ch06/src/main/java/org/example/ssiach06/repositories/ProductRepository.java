package org.example.ssiach06.repositories;

import org.example.ssiach06.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
