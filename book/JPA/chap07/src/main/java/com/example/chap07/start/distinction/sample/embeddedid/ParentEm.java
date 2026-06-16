package com.example.chap07.start.distinction.sample.embeddedid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@ToString
public class ParentEm {

    @EmbeddedId
    private ParentIdEm id;

    private String name;
}
