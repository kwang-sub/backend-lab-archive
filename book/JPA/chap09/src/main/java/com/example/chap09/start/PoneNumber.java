package com.example.chap09.start;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PoneNumber {
    private String areaCode;
    private String localNumber;
    @ManyToOne
    private PhoneServiceProvider provider;
}
