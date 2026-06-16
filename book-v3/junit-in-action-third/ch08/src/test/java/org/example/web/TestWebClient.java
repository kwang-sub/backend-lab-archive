package org.example.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.*;

public class TestWebClient {

    @Test
    void testGetContentOk() throws Exception {
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        mockConnectionFactory.setData(new ByteArrayInputStream("It works".getBytes()));
        WebClient2 webClient2 = new WebClient2();
        String content = webClient2.getContent(mockConnectionFactory);
        assertThat(content).isEqualTo("It works");
    }
}
