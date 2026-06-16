package org.example.ex05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockTest {

    @Mock
    private IEmailGateway emailGateway;
    @Mock
    private IDatabase database;

    @Test
    void sending_a_greetings_email() {
        // given
        Controller sut = new Controller(emailGateway, database);
        // when
        sut.greetUser("user@email.com");
        // then
        verify(emailGateway, times(1)).sendGreetingsEmail("user@email.com");
    }

    @Test
    void creating_a_report() {
        // given
        Mockito.when(database.getNumberOfUsers()).thenReturn(10);
        Controller sut = new Controller(emailGateway, database);
        // when
        Report report = sut.createReport();
        // then
        assertThat(report.getNumberOfUsers()).isEqualTo(10);
        verify(database, times(1)).getNumberOfUsers();
    }
}
