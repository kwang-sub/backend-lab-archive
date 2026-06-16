package com.example.chap07.start.distinction.idclass;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class GrandChildId implements Serializable {

    private ChildId child;
    private String id;
}
