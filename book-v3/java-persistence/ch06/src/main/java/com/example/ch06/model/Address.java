package com.example.ch06.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotNull
    @Column(nullable = false)
    private String street;

    @AttributeOverride(
            name = "name",
            column = @Column(name = "CITY", nullable = false)
    )
    private City city;



    public static Address create(String street, String zipcode, String city, String country) {
        City cityClass = City.builder()
                .zipcode(new GermanZipcode(zipcode))
                .name(city)
                .country(country)
                .build();

        return Address.builder()
                .street(street)
                .city(cityClass)
                .build();
    }
}
