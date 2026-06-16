package org.example.ssiach11ex1s2.security.util;

import lombok.RequiredArgsConstructor;
import org.example.ssiach11ex1s2.security.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthenticationServerProxy {

    private final RestTemplate rest;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String username, String password) {
        final String url = baseUrl + "/user/auth";
        final var body = User.create(username, password, null);
        final var request = new HttpEntity<>(body);
        rest.postForEntity(url, request, Void.class);
    }


    public boolean sendOtp(String username, String code) {
        final String url = baseUrl + "/otp/check";
        final var body = User.create(username, null, code);
        final var request = new HttpEntity<>(body);
        final var response = rest.postForEntity(url, request, Void.class);

        return response.getStatusCode().is2xxSuccessful();
    }
}
