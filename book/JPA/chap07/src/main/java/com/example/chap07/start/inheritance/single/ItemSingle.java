package com.example.chap07.start.inheritance.single;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_SINGLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class ItemSingle {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}
