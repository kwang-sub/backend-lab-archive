package org.example.ssiach11ex1s1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Otp {

    @Id
    private String username;
    private String code;

    public static Otp create(String username, String code) {
        return Otp.builder()
                .username(username)
                .code(code)
                .build();
    }

    public void updateCode(String code) {
        this.code = code;
    }
}
