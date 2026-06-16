package org.example.ssiach11ex1s2.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String code;

    public static User create(String username, String password, String code) {
        return User.builder()
                .username(username)
                .password(password)
                .code(code)
                .build();
    }
}
