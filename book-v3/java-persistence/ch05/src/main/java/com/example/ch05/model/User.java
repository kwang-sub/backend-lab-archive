package com.example.ch05.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
@Immutable
public class User {

    @Id
    private Long id;
}
