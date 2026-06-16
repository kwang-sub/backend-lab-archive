package com.example.chap06.start.mapping;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Setter
public class MemberProductId implements Serializable {

    private Long member;
    private String product;
}
