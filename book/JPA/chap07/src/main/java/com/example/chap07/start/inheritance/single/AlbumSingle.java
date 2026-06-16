package com.example.chap07.start.inheritance.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class AlbumSingle extends ItemSingle {

    private String albumColum;
}
