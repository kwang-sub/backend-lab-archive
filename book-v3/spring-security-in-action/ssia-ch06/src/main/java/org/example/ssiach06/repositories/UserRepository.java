package org.example.ssiach06.repositories;

import org.example.ssiach06.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findUserByUsername(String username);
}
