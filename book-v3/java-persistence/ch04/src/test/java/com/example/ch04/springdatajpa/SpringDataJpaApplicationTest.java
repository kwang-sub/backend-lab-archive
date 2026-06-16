package com.example.ch04.springdatajpa;

import com.example.ch04.model.Projection;
import com.example.ch04.model.User;
import com.example.ch04.repositories.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpringDataJpaApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        userRepository.saveAll(generateUser());
    }

    private Iterable<User> generateUser() {
        List<User> users = new ArrayList<>();
        User user1 = User.builder().username("beth1").level(0).email("beth1@gmail.com").registrationDate(null).build();
        User user2 = User.builder().username("beth2").level(0).email("beth2@gmail.com").registrationDate(null).build();
        User user3 = User.builder().username("beth3").level(0).email("beth3@gmail.com").registrationDate(null).build();
        User user4 = User.builder().username("beth4").level(0).email("beth4@gmail.com").registrationDate(null).build();
        User user5 = User.builder().username("beth5").level(0).email("beth5@gmail.com").registrationDate(null).build();
        User user6 = User.builder().username("beth6").level(0).email("beth6@gmail.com").registrationDate(null).build();
        User user7 = User.builder().username("b7").level(1).email("beth7@gmail.com").registrationDate(null).build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        return users;
    }

    @AfterAll
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(7);
    }

    @Test
    void testFindUser() {
        User users = userRepository.findByUsername("beth7");
        assertThat(users.getUsername()).isEqualTo("beth7");
    }

    @Test
    void testStreamable() {
        try (Stream<User> userStream = userRepository.findByEmailContaining("beth").and(userRepository.findByLevel(1))
                .stream().distinct();
        ) {
            assertThat(userStream.count()).isEqualTo(7);
        }
    }

    @Test
    void queryTest() {
        int cnt = userRepository.countByActive(false);
        assertThat(cnt).isEqualTo(7);

        List<User> username = userRepository.findByActiveAndSort(false, Sort.by(Sort.Direction.DESC, "username"));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(username.size()).isEqualTo(7);
        softly.assertThat(username.get(0).getUsername()).isEqualTo("beth6");

        softly.assertAll();
    }

    @Test
    void projectionTest() {
        List<Projection.UsernameOnly> onlyByEmail = userRepository.findOnlyByEmail("beth1@gmail.com");
        assertThat(onlyByEmail.size()).isEqualTo(1);
        assertThat(onlyByEmail.get(0).getUsername()).isEqualTo("beth1");

        List<Projection.UserSummary> summaryByEmail = userRepository.findSummaryByEmail("beth1@gmail.com");
        assertThat(summaryByEmail.size()).isEqualTo(1);
        assertThat(summaryByEmail.get(0).getInfo()).isEqualTo("beth1 beth1@gmail.com");
    }

    @Test
    void testEmailWithQueryByExample() {
        User user = User.builder().email("@gmail.com").build();

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("level", "active")
                .withMatcher("email", match -> match.endsWith());

        Example<User> example = Example.of(user, matcher);
        List<User> users = userRepository.findAll(example);
        assertThat(users.size()).isEqualTo(7);
    }

}
