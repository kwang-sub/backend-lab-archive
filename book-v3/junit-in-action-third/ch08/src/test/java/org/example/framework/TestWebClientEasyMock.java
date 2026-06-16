package org.example.framework;

import org.assertj.core.api.Assertions;
import org.example.web.ConnectionFactory;
import org.example.web.WebClient2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;

public class TestWebClientEasyMock {
    private ConnectionFactory factory;
    private InputStream inputStream;

    @BeforeEach
    public void setUp() {
        factory = createMock("factory", ConnectionFactory.class);
        inputStream = createMock("stream", InputStream.class);
    }

    @Test
    void testGetContentOk() throws Exception {
        expect(factory.getData()).andReturn(inputStream);
        expect(inputStream.read()).andReturn(Integer.valueOf((byte) 'W'));
        expect(inputStream.read()).andReturn(Integer.valueOf((byte) 'o'));
        expect(inputStream.read()).andReturn(Integer.valueOf((byte) 'r'));
        expect(inputStream.read()).andReturn(Integer.valueOf((byte) 'k'));
        expect(inputStream.read()).andReturn(Integer.valueOf((byte) 's'));
        expect(inputStream.read()).andReturn(Integer.valueOf((byte) '!'));
        expect(inputStream.read()).andReturn(-1);
        inputStream.close();

        replay(factory, inputStream);

        WebClient2 client = new WebClient2();
        String content = client.getContent(factory);
        assertThat(content).isEqualTo("Works!");
    }

    @Test
    void testGetContentCannotCloseInputStream() throws Exception {
        expect(factory.getData()).andReturn(inputStream);
        expect(inputStream.read()).andReturn(-1);
        inputStream.close();
        expectLastCall().andThrow(new IOException("cannot close"));


        replay(factory);
        replay(inputStream);

        WebClient2 client = new WebClient2();
        String content = client.getContent(factory);
        assertThat(content).isNull();
    }

    @AfterEach
    void tearDown() {
        verify(factory);
        verify(inputStream);
    }

}
