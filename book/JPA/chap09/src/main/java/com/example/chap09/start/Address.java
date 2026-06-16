package com.example.chap09.start;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Address {

    @Column
    private String city;
    private String street;
    private String zipcode;

    public Address(String city) {
        this.city = city;
    }
}
