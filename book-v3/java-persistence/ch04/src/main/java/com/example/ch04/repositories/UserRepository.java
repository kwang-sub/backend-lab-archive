package com.example.ch04.repositories;

import com.example.ch04.model.Projection;
import com.example.ch04.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();

    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);

    List<User> findByUsernameAndEmail(String username, String email);

    Streamable<User> findByEmailContaining(String email);

    Streamable<User> findByLevel(int level);

    @Query("select count(u) from User u where u.active = :active")
    int countByActive(boolean active);

    @Query("select u from User u where u.active = :active")
    List<User> findByActiveAndSort(boolean active, Sort sort);

    List<Projection.UserSummary> findSummaryByEmail(String email);

    List<Projection.UsernameOnly> findOnlyByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.level = :newLever where u.level = :oldLevel")
    int updateLevel(int oldLevel, int newLever);
}
