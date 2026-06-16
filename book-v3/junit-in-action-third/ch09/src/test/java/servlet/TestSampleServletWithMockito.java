package servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestSampleServletWithMockito {

    private SampleServlet sampleServlet;

    @Mock
    private HttpServletRequest mockHttpServletRequest;

    @Mock
    private HttpSession mockHttpSession;

    @BeforeEach
    void setUp() {
        sampleServlet = new SampleServlet();
    }

    @Test
    void testIsAuthenticatedAuthenticated() {
        when(mockHttpSession.getAttribute(eq("authenticated"))).thenReturn("true");
        when(mockHttpServletRequest.getSession(eq(false))).thenReturn(mockHttpSession);

        assertThat(sampleServlet.isAuthenticated(mockHttpServletRequest)).isTrue();
    }

    @Test
    void testIsAuthenticatedNotAuthenticated() {
        when(mockHttpSession.getAttribute(eq("authenticated"))).thenReturn("false");
        when(mockHttpServletRequest.getSession(eq(false))).thenReturn(mockHttpSession);

        assertThat(sampleServlet.isAuthenticated(mockHttpServletRequest)).isFalse();
    }

    @Test
    void testIsAuthenticateNotSession() {
        when(mockHttpServletRequest.getSession(eq(false))).thenReturn(null);

        assertThat(sampleServlet.isAuthenticated(mockHttpServletRequest)).isFalse();
    }
}