package org.example.ex07.canexecute;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void changing_email_from_non_corporate_to_corporate() {
        // given
        Company company = new Company("mycorp.com", 1);
        User user = new User(1, "user@gmail.com", UserType.CUSTOMER, false);
        // when
        user.changeEmail("new@mycorp.com", company);
        // then
        assertThat(company.getNumberOfEmployees()).isEqualTo(2);
        assertThat(user.getEmail()).isEqualTo("new@mycorp.com");
        assertThat(user.getType()).isEqualTo(UserType.EMPLOYEE);
    }

    @ParameterizedTest
    @CsvSource({
            "mycorp.com, email@mycorp.com, true",     // 첫 번째 테스트 케이스
            "mycorp.com, email@gmail.com, false"      // 두 번째 테스트 케이스
    })
    void differentiates_a_corporate_email_from_non_corporate(
            String domain, String email, boolean expectedResult
    ) {
        // given
        Company company = new Company(domain, 0);

        // when
        boolean isEmailCorporate = company.isEmailCorporate(email);

        // then
        assertThat(isEmailCorporate).isEqualTo(expectedResult);
    }

}