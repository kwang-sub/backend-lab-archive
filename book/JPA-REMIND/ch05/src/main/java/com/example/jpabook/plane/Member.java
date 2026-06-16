package com.example.jpabook.plane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
    private String id;
    private String username;
    private Team team;

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
