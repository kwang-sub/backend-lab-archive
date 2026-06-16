package org.example.ssiach11ex1s2.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.ssiach11ex1s2.security.filter.InitialAuthenticationFilter;
import org.example.ssiach11ex1s2.security.filter.JwtAuthenticationFilter;
import org.example.ssiach11ex1s2.security.provider.OtpAuthenticationProvider;
import org.example.ssiach11ex1s2.security.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OtpAuthenticationProvider otpAuthenticationProvider;
    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;


    @Bean
    protected InitialAuthenticationFilter initialAuthenticationFilter() {
        return new InitialAuthenticationFilter(authenticationManager());
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(otpAuthenticationProvider)
                .authenticationProvider(usernamePasswordAuthenticationProvider)
                .csrf(csrf -> csrf.disable())
                .addFilterAt(initialAuthenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(usernamePasswordAuthenticationProvider, otpAuthenticationProvider));
    }
}
