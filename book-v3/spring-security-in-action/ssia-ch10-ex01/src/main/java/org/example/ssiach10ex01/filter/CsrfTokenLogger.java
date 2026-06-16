package org.example.ssiach10ex01.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.security.web.csrf.CsrfToken;

public class CsrfTokenLogger implements Filter {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Object o = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) o;
        logger.info("Csrf token: " + token.getToken());

        chain.doFilter(request, response);
    }
}
