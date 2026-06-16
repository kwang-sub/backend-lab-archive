package org.example.framework;

import org.assertj.core.api.Assertions;
import org.example.web.ConnectionFactory;
import org.example.web.WebClient2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestWebClientMockito {
    @Mock
    private ConnectionFactory mockConnectionFactory;

    @Mock
    private InputStream mockInputStream;

    @Test
    void testWebClient() throws Exception {
        lenient().when(mockConnectionFactory.getData())
                .thenReturn(mockInputStream);

        lenient().when(mockInputStream.read())
                .thenReturn((int) 'W')
                .thenReturn((int) 'o')
                .thenReturn((int) 'r')
                .thenReturn((int) 'k')
                .thenReturn((int) 's')
                .thenReturn((int) '!')
                .thenReturn(-1);

        WebClient2 webClient2 = new WebClient2();
        String content = webClient2.getContent(mockConnectionFactory);

        assertThat(content).isEqualTo("Works!");
    }

    @Test
    void testGEtContentCannotCloseInputStream() throws Exception {
        when(mockConnectionFactory.getData()).thenReturn(mockInputStream);
        when(mockInputStream.read()).thenReturn(-1);
        doThrow(new IOException("cannot close")).when(mockInputStream).close();

        WebClient2 webClient2 = new WebClient2();
        String content = webClient2.getContent(mockConnectionFactory);
        assertThat(content).isNull();
    }
}
