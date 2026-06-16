package com.example.chap07.start.inheritance.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class BookSingle extends ItemSingle {

    private String bookColum;
}
