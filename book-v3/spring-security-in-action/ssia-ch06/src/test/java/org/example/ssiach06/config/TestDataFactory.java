package org.example.ssiach06.config;

import org.example.ssiach06.entities.Product;
import org.example.ssiach06.entities.Users;
import org.example.ssiach06.entities.enums.Currency;
import org.example.ssiach06.entities.enums.EncryptionAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@TestComponent
public class TestDataFactory {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users creatUser() {
        return Users.builder()
                .username("kwang")
                .password(bCryptPasswordEncoder.encode("123456"))
                .algorithm(EncryptionAlgorithm.BCRYPT)
                .build();
    }

    public Product createProducts() {
        return Product.builder()
                .name("Chocolate")
                .price(10)
                .currency(Currency.USD)
                .build();
    }
}
