package com.example.chap07.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
