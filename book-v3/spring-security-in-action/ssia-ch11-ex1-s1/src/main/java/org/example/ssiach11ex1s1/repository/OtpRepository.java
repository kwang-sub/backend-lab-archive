package org.example.ssiach11ex1s1.repository;

import java.util.Optional;
import org.example.ssiach11ex1s1.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByUsername(String username);
}
