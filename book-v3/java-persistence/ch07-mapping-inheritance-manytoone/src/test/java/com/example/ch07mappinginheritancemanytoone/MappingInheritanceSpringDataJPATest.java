package com.example.ch07mappinginheritancemanytoone;

import com.example.ch07mappinginheritancemanytoone.model.BillingDetails;
import com.example.ch07mappinginheritancemanytoone.model.CreditCard;
import com.example.ch07mappinginheritancemanytoone.model.User;
import com.example.ch07mappinginheritancemanytoone.repositories.CreditCardRepository;
import com.example.ch07mappinginheritancemanytoone.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class MappingInheritanceSpringDataJPATest {

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {
        CreditCard creditCard = new CreditCard("John Smith", "123456789", "10", "2030");
        User user = User.builder()
                .username("John Smith")
                .billingDetails(creditCard)
                .build();

        creditCardRepository.save(creditCard);
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        BillingDetails billingDetails = users.get(0).getBillingDetails();
        billingDetails.pay(123);
    }
}
