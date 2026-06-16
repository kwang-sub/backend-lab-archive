package org.example.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MockInputStreamTest {
    @Test
    void testGetContentOk() {
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();

        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.setBuffer("It works");

        mockConnectionFactory.setData(mockInputStream);

        WebClient2 webClient2 = new WebClient2();
        String content = webClient2.getContent(mockConnectionFactory);

        assertThat(content).isEqualTo("It works");
        mockInputStream.verify();
    }
}