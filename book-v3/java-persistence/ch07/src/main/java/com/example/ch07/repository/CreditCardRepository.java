package com.example.ch07.repository;

import com.example.ch07.model.BankAccount;
import com.example.ch07.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findByExpYear(String expYear);
    List<CreditCard> findByOwner(String owner);

}
