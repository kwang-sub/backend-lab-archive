package com.example.chap10.start.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
