package com.example.ch07.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("CC")
//@SecondaryTable(
//        name = "CREDITCARD",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name = "CREDITCARD_ID")
//)
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends BillingDetails {

    @Column( nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String expMonth;

    @Column(nullable = false)
    private String expYear;
}
