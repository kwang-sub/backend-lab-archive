package org.example.displayname;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class showing the @DisplayName annotation.")
class SUTTest {

    private SUT sut = new SUT();

    @Test
    @DisplayName("Our system under test says hello.")
    void testHello() {
        assertEquals("Hello", sut.hello());
    }

    @Test
    @DisplayName("♣")
    void testTalking() {
        assertEquals("How are you?", sut.talk());
    }

    @Test
    void testBye() {
        assertEquals("Bye", sut.bye());
    }
}