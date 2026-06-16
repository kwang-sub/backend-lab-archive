package org.example.market.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "customer")
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    public static Customer create(final String name, final String encodedPassword) {
        return Customer.builder()
                .name(name)
                .password(encodedPassword)
                .build();
    }
}