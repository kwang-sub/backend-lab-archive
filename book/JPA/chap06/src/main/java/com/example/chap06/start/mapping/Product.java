package com.example.chap06.start.mapping;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Product {

    @Id @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

/*
    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();
*/

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }
}