package com.example.chap07.start.join.ntn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Child {
    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    @OneToMany
    private List<Parent> parents = new ArrayList<>();
}
