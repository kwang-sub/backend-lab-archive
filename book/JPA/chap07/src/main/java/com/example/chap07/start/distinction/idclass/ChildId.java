package com.example.chap07.start.distinction.idclass;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ChildId implements Serializable {

    private String parent;
    private String childId;
}
