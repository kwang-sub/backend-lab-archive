package com.example.ch06.model;

import com.example.ch06.converter.ZipcodeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    @Convert(converter = ZipcodeConverter.class, attributeName = "city.zipcode")
    private Address homeAddress;

    @Embedded
    @AttributeOverride(name = "street", column = @Column(name = "BILLING_STREET"))
    @AttributeOverride(name = "city.zipcode", column = @Column(name = "BILLING_ZIPCODE", length = 5))
    @AttributeOverride(name = "city.name", column = @Column(name = "BILLING_CITY"))
    @AttributeOverride(name = "city.country", column = @Column(name = "BILLING_COUNTRY"))
    @Convert(converter = ZipcodeConverter.class, attributeName = "city.zipcode")
    private Address billingAddress;
}
