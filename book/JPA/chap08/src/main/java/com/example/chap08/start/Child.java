package com.example.chap08.start;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Child {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    public void setParent(Parent parent) {
        parent.getChildren().add(this);
        this.parent = parent;
    }
}
