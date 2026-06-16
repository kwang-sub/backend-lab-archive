package com.example.chap07.start.inheritance.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class MovieSingle extends ItemSingle {

    private String movieColum;
}
