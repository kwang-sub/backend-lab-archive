package org.example.ssiach15ex1as.config;

import static org.springframework.security.config.Customizer.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

@Configuration
public class WebConfig {

    /**
     * Protocol endpoints 를 위한 설정
     */
//    @Bean
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(withDefaults());	// OpenID Connect 1.0 사용
//        http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor( // 인가 실패에 대한 처리를 정의
//                new LoginUrlAuthenticationEntryPoint("/login"),
//                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
//        ));
//        http.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(withDefaults())); // '토큰 검증'에 대한 설정
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        // Token endpoint 설정: AuthenticationManager 사용
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
//                .tokenEndpoint(tokenEndpoint -> tokenEndpoint.authenticationManager(authenticationManager()));  // AuthenticationManager 설정

        // OpenID Connect 1.0 사용 (필요시)
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(withDefaults());

        // 예외 처리: 인증 실패 시 로그인 페이지로 리다이렉트
        http.exceptionHandling(exceptions -> exceptions.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint("/login"),
                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
        ));

        // Resource Server JWT 검증 설정
        http.oauth2ResourceServer(resourceServer -> resourceServer.jwt(withDefaults()));

        return http.build();
    }



    /**
     * 인증(Authentication)을 위한 설정
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers("/main").permitAll()
                    .anyRequest().authenticated();
        });
        http.formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId("kwang")
                .clientId("client") // 클라이언트 ID
                .clientName("client")
                .clientSecret("secret") // 클라이언트 비밀번호 (NoOp 인코딩 사용)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // 권한 부여 방식
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) // 리프레시 토큰 지원
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // 클라이언트 자격증명
//                .redirectUri("http://localhost:8080/login/oauth2/code/client-oidc") // 리다이렉션 URI
                .redirectUri("http://localhost:8080/main")
                .scope("read") // 클라이언트 권한 범위
                .scope("write")
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }


    @Bean
    public JwtDecoder jwtDecoder(KeyPair keyPair) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }

    @Bean
    public KeyPair keyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();

        var user = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();

        uds.createUser(user);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
