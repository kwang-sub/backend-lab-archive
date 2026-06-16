package org.example.ssiach11ex1s1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Users {

    @Id
    private String username;

    private String password;

    public void updatePassword(String password) {
        this.password = password;
    }
}
