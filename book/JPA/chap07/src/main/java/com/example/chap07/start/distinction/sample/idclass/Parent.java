package com.example.chap07.start.distinction.sample.idclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ParentId.class)
@Setter
@Getter
@ToString
public class Parent {

    @Id @Column(name = "PARENT_ID1")
    private String id1;

    @Id @Column(name = "PARENT_ID2")
    private String id2;

    private String name;
}

