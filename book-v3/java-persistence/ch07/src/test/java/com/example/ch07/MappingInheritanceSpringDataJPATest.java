package com.example.ch07;

import com.example.ch07.model.BankAccount;
import com.example.ch07.model.CreditCard;
import com.example.ch07.repository.BankAccountRepository;
import com.example.ch07.repository.BillingDetailsRepository;
import com.example.ch07.repository.CreditCardRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MappingInheritanceSpringDataJPATest {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;


    @Test
    void storeLoadEntities() {
        CreditCard creditCard = CreditCard.builder()
                .owner("John Smith")
                .cardNumber("12345")
                .expMonth("10")
                .expYear("2030")
                .build();
        creditCardRepository.save(creditCard);

        BankAccount bankAccount = BankAccount.builder()
                .owner("Mike Johnson")
                .account("12345")
                .bankName("Delta Bank")
                .swift("BANKXY12")
                .build();
        bankAccountRepository.save(bankAccount);

        List<CreditCard> creditCards = creditCardRepository.findByOwner("John Smith");
        List<BankAccount> bankAccounts = bankAccountRepository.findByOwner("Mike Johnson");
        billingDetailsRepository.findAll();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(creditCards).hasSize(1);
        softAssertions.assertThat(creditCards.get(0).getCardNumber()).isEqualTo("12345");

        softAssertions.assertThat(bankAccounts).hasSize(1);
        softAssertions.assertThat(bankAccounts.get(0).getAccount()).isEqualTo("12345");

        softAssertions.assertAll();
    }


}
