package org.example.ssiach11ex1s1.repository;

import java.util.Optional;
import org.example.ssiach11ex1s1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findUserByUsername(String username);
}
