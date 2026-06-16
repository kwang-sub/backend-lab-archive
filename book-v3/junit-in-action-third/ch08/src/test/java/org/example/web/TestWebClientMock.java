package org.example.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class TestWebClientMock {
    @Test
    void testGetContentOk() throws Exception {
        // given
        MockHttpURLConnection mockConnection = new MockHttpURLConnection();
        mockConnection.setExpectedInputStream(new ByteArrayInputStream("It works".getBytes()));

        TestableWebClient client = new TestableWebClient();
        client.setHttpURLConnection(mockConnection);

        // when
        String content = client.getContent(new URL("http://localhost"));

        // then
        assertThat(content).isEqualTo("It works");
    }
}
