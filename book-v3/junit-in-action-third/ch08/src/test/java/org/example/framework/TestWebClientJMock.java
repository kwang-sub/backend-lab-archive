package org.example.framework;

import org.assertj.core.api.Assertions;
import org.example.account.Account;
import org.example.account.AccountManager;
import org.example.account.AccountService;
import org.example.web.ConnectionFactory;
import org.example.web.WebClient2;
import org.jmock.Expectations;
import org.jmock.junit5.JUnit5Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestWebClientJMock {

    @RegisterExtension
    JUnit5Mockery context = new JUnit5Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Test
    void testTransferOk() throws Exception {
        ConnectionFactory mockFactory = context.mock(ConnectionFactory.class);
        InputStream mockStream = context.mock(InputStream.class);

        context.checking(new Expectations() {
            {
                oneOf(mockFactory).getData();
                will(returnValue(mockStream));

                atLeast(1).of(mockStream).read();
                will(onConsecutiveCalls(
                        returnValue(Integer.valueOf((byte) 'W')),
                        returnValue(Integer.valueOf((byte) 'o')),
                        returnValue(Integer.valueOf((byte) 'r')),
                        returnValue(Integer.valueOf((byte) 'k')),
                        returnValue(Integer.valueOf((byte) 's')),
                        returnValue(Integer.valueOf((byte) '!')),
                        returnValue(-1))
                );
                oneOf(mockStream).close();
            }
        });

        WebClient2 webClient = new WebClient2();
        String content = webClient.getContent(mockFactory);

        assertThat(content).isEqualTo("Works!");
    }

    @Test
    void testGetContentTCannotCloseInputStream() throws Exception {
        ConnectionFactory mockFactory = context.mock(ConnectionFactory.class);
        InputStream mockStream = context.mock(InputStream.class);
        context.checking(new Expectations() {
            {
                oneOf(mockFactory).getData();
                will(returnValue(mockStream));
                oneOf(mockStream).read();
                will(returnValue(-1));
                oneOf(mockStream).close();
                will(throwException(new IOException("cannot close")));
            }
        });

        WebClient2 webClient = new WebClient2();
        String content = webClient.getContent(mockFactory);
        assertThat(content).isNull();
    }

}
