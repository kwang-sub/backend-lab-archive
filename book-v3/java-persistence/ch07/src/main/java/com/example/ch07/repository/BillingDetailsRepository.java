package com.example.ch07.repository;

import com.example.ch07.model.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {
    List<BillingDetails> findByOwner(String owner);
}
