package com.example.ch07.repository;

import com.example.ch07.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    List<BankAccount> findBySwift(String swift);
    List<BankAccount> findByOwner(String owner);
}
